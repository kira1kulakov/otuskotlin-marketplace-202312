package ru.otus.otuskotlin.social.moodnow.common.repo

interface IRepoChatter {
    suspend fun createChatter(rq: DbChatterRequest): IDbChatterResponse

    suspend fun readChatter(rq: DbChatterIdRequest): IDbChatterResponse

    suspend fun updateChatter(rq: DbChatterRequest): IDbChatterResponse

    suspend fun deleteChatter(rq: DbChatterIdRequest): IDbChatterResponse

    companion object {
        val NONE = object : IRepoChatter {
            override suspend fun createChatter(rq: DbChatterRequest): IDbChatterResponse {
                throw NotImplementedError("Must not be used")
            }

            override suspend fun readChatter(rq: DbChatterIdRequest): IDbChatterResponse {
                throw NotImplementedError("Must not be used")
            }

            override suspend fun updateChatter(rq: DbChatterRequest): IDbChatterResponse {
                throw NotImplementedError("Must not be used")
            }

            override suspend fun deleteChatter(rq: DbChatterIdRequest): IDbChatterResponse {
                throw NotImplementedError("Must not be used")
            }
        }
    }
}
