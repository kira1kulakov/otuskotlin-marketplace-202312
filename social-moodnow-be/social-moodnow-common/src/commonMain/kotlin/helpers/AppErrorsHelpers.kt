package ru.otus.otuskotlin.social.moodnow.common.helpers

import ru.otus.otuskotlin.social.moodnow.common.AppContext
import ru.otus.otuskotlin.social.moodnow.common.enums.State
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

inline fun AppContext.addError(vararg error: ModelError) = errors.addAll(error)

inline fun AppContext.fail(error: ModelError) {
    addError(error)
    state = State.FAILING
}
