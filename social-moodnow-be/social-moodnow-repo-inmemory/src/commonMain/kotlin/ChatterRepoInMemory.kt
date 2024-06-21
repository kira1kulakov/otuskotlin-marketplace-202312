package ru.otus.otuskotlin.social.moodnow.repo.inmemory

import com.benasher44.uuid.uuid4
import io.github.reactivecircus.cache4k.Cache
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import ru.otus.otuskotlin.social.moodnow.common.models.*
import ru.otus.otuskotlin.social.moodnow.common.repo.*
import ru.otus.otuskotlin.social.moodnow.repo.common.IRepoChatterInitializable
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

class ChatterRepoInMemory(
    ttl: Duration = 2.minutes,
    val randomUuid: () -> String = { uuid4().toString() },
) : IRepoChatter, IRepoChatterInitializable {

    private val mutex: Mutex = Mutex()
    private val cache = Cache.Builder<String, ChatterEntity>()
        .expireAfterWrite(ttl)
        .build()

    override fun save(chatters: Collection<ModelBaseChatter>) = chatters.map { chatter ->
        val entity = ChatterEntity(chatter)
        require(entity.id != null)
        cache.put(entity.id, entity)
        entity.toInternal()
    }

    override suspend fun createChatter(rq: DbChatterRequest): IDbChatterResponse {
        val key = randomUuid()
        val chatter = rq.chatter.copy(id = ModelChatterId(key))
        val entity = ChatterEntity(chatter)
        cache.put(key, entity)
        return DbChatterResponseOk(entity.toInternal())
    }

    override suspend fun readChatter(rq: DbChatterIdRequest): IDbChatterResponse {
        val key = rq.id.takeIf { it != ModelChatterId.NONE }?.asString() ?: return errorEmptyId
        return cache.get(key)
            ?.let {
                DbChatterResponseOk(it.toInternal())
            } ?: errorNotFound(rq.id)
    }

    override suspend fun updateChatter(rq: DbChatterRequest): IDbChatterResponse {
        val rqChatter = rq.chatter
        val id = rqChatter.id.takeIf { it != ModelChatterId.NONE } ?: return errorEmptyId
        val key = id.asString()

        return mutex.withLock {
            val oldAChatter = cache.get(key)?.toInternal()
            when {
                oldAChatter == null -> errorNotFound(id)
                else -> {
                    val newAChatter = rqChatter.copy()
                    val entity = ChatterEntity(newAChatter)
                    cache.put(key, entity)
                    DbChatterResponseOk(entity.toInternal())
                }
            }
        }
    }

    override suspend fun deleteChatter(rq: DbChatterIdRequest): IDbChatterResponse {
        val id = rq.id.takeIf { it != ModelChatterId.NONE } ?: return errorEmptyId
        val key = id.asString()

        return mutex.withLock {
            val oldChatter = cache.get(key)?.toInternal()
            when {
                oldChatter == null -> errorNotFound(id)
                else -> {
                    cache.invalidate(key)
                    DbChatterResponseOk(oldChatter)
                }
            }
        }
    }
}
