package ru.otus.otuskotlin.social.moodnow.common.repo

import ru.otus.otuskotlin.social.moodnow.common.models.ModelChatterId
import ru.otus.otuskotlin.social.moodnow.common.models.ModelError

const val ERROR_GROUP_REPO = "repo"

fun errorNotFound(id: ModelChatterId) = DbChatterResponseErr(
    ModelError(
        code = "$ERROR_GROUP_REPO-not-found",
        group = ERROR_GROUP_REPO,
        field = "id",
        message = "Object with ID: ${id.asString()} is not Found",
    )
)

val errorEmptyId = DbChatterResponseErr(
    ModelError(
        code = "$ERROR_GROUP_REPO-empty-id",
        group = ERROR_GROUP_REPO,
        field = "id",
        message = "Id must not be null or blank"
    )
)
