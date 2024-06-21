package ru.otus.otuskotlin.social.moodnow.repo.stubs

import ru.otus.otuskotlin.social.moodnow.common.repo.*
import ru.otus.otuskotlin.social.moodnow.stubs.AppChatterStub

class ChatterRepoStub() : IRepoChatter {
    override suspend fun createChatter(rq: DbChatterRequest): IDbChatterResponse {
        return DbChatterResponseOk(
            data = AppChatterStub.getAnyResponse(),
        )
    }

    override suspend fun readChatter(rq: DbChatterIdRequest): IDbChatterResponse {
        return DbChatterResponseOk(
            data = AppChatterStub.getAnyResponse(),
        )
    }

    override suspend fun updateChatter(rq: DbChatterRequest): IDbChatterResponse {
        return DbChatterResponseOk(
            data = AppChatterStub.getAnyResponse(),
        )
    }

    override suspend fun deleteChatter(rq: DbChatterIdRequest): IDbChatterResponse {
        return DbChatterResponseOk(
            data = AppChatterStub.getAnyResponse(),
        )
    }
}
