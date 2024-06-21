package ru.otus.otuskotlin.social.moodnow.backend.repo.tests

import ru.otus.otuskotlin.social.moodnow.common.models.ModelBaseChatter
import ru.otus.otuskotlin.social.moodnow.common.repo.*

class ChatterRepositoryMock(
    private val invokeCreateChatter: (DbChatterRequest) -> IDbChatterResponse = { DEFAULT_SUCCESS_EMPTY_MOCK },
    private val invokeReadChatter: (DbChatterIdRequest) -> IDbChatterResponse = { DEFAULT_SUCCESS_EMPTY_MOCK },
    private val invokeUpdateChatter: (DbChatterRequest) -> IDbChatterResponse = { DEFAULT_SUCCESS_EMPTY_MOCK },
    private val invokeDeleteChatter: (DbChatterIdRequest) -> IDbChatterResponse = { DEFAULT_SUCCESS_EMPTY_MOCK },
) : IRepoChatter {
    override suspend fun createChatter(rq: DbChatterRequest): IDbChatterResponse {
        return invokeCreateChatter(rq)
    }

    override suspend fun readChatter(rq: DbChatterIdRequest): IDbChatterResponse {
        return invokeReadChatter(rq)
    }

    override suspend fun updateChatter(rq: DbChatterRequest): IDbChatterResponse {
        return invokeUpdateChatter(rq)
    }

    override suspend fun deleteChatter(rq: DbChatterIdRequest): IDbChatterResponse {
        return invokeDeleteChatter(rq)
    }

    companion object {
        val DEFAULT_SUCCESS_EMPTY_MOCK = DbChatterResponseOk(ModelBaseChatter())
    }
}
