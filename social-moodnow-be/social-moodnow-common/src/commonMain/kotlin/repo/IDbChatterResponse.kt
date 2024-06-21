package ru.otus.otuskotlin.social.moodnow.common.repo

import ru.otus.otuskotlin.social.moodnow.common.models.ModelBaseChatter
import ru.otus.otuskotlin.social.moodnow.common.models.ModelError

sealed interface IDbChatterResponse : IDbResponse<ModelBaseChatter>

data class DbChatterResponseOk(
    val data: ModelBaseChatter
) : IDbChatterResponse

data class DbChatterResponseErr(
    val errors: List<ModelError> = emptyList()
) : IDbChatterResponse {
    constructor(err: ModelError) : this(listOf(err))
}

data class DbChatterResponseErrWithData(
    val data: ModelBaseChatter,
    val errors: List<ModelError> = emptyList()
) : IDbChatterResponse {
    constructor(chatter: ModelBaseChatter, err: ModelError) : this(chatter, listOf(err))
}