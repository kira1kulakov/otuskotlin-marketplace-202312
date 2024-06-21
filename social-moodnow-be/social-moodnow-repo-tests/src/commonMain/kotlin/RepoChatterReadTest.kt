package ru.otus.otuskotlin.social.moodnow.backend.repo.tests

import ru.otus.otuskotlin.social.moodnow.common.models.ModelBaseChatter
import ru.otus.otuskotlin.social.moodnow.common.models.ModelChatterId
import ru.otus.otuskotlin.social.moodnow.common.repo.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs


abstract class RepoChatterReadTest {
    abstract val repo: IRepoChatter
    protected open val readSucc = initObjects[0]

    @Test
    fun readSuccess() = runRepoTest {
        val result = repo.readChatter(DbChatterIdRequest(readSucc.id))

        assertIs<DbChatterResponseOk>(result)
        assertEquals(readSucc.id, result.data.id)
        assertEquals(readSucc.message, result.data.message)
    }

    @Test
    fun readNotFound() = runRepoTest {
        val result = repo.readChatter(DbChatterIdRequest(notFoundId))

        assertIs<DbChatterResponseErr>(result)
        val error = result.errors.find { it.code == "repo-not-found" }
        assertEquals("id", error?.field)
    }

    companion object : BaseInitChatters("delete") {
        override val initObjects: List<ModelBaseChatter> = listOf(
            createInitTestModel("read")
        )

        val notFoundId = ModelChatterId("chatter-repo-read-notFound")
    }
}
