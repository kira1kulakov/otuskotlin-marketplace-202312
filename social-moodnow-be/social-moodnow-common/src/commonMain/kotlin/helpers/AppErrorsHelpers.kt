package ru.otus.otuskotlin.social.moodnow.common.helpers

import ru.otus.otuskotlin.social.moodnow.common.AppContext
import ru.otus.otuskotlin.social.moodnow.common.enums.State
import ru.otus.otuskotlin.social.moodnow.common.models.ModelError
import ru.otus.otuskotlin.social.moodnow.logging.common.LogLevel

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

inline fun AppContext.addError(error: ModelError) = errors.add(error)

inline fun AppContext.addErrors(error: Collection<ModelError>) = errors.addAll(error)

inline fun AppContext.fail(error: ModelError) {
    addError(error)
    state = State.FAILING
}

inline fun AppContext.fail(errors: Collection<ModelError>) {
    addErrors(errors)
    state = State.FAILING
}

inline fun errorSystem(
    violationCode: String,
    level: LogLevel = LogLevel.ERROR,
    e: Throwable,
) = ModelError(
    code = "system-$violationCode",
    group = "system",
    message = "System error occurred. Our stuff has been informed, please retry later",
    level = level,
    exception = e,
)

inline fun errorValidation(
    field: String,
    violationCode: String,
    description: String,
    level: LogLevel = LogLevel.ERROR,
) = ModelError(
    code = "validation-$field-$violationCode",
    field = field,
    group = "validation",
    message = "Validation error for field $field: $description",
    level = level,
)