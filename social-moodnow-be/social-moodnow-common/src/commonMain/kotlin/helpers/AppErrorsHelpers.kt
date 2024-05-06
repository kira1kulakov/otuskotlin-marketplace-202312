package ru.otus.otuskotlin.social.moodnow.common.helpers

import ru.otus.otuskotlin.social.moodnow.common.models.ModelError

fun Throwable.asMkplError(
    code: String = "unknown",
    group: String = "exceptions",
    message: String = this.message ?: "",
) = ModelError(
    code = code,
    group = group,
    field = "",
    message = message,
    exception = this,
)
