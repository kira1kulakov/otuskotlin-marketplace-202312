package ru.otus.otuskotlin.social.moodnow.common.models

import ru.otus.otuskotlin.social.moodnow.logging.common.LogLevel

data class ModelError(
    val code: String = "",
    val group: String = "",
    val field: String = "",
    val message: String = "",
    val level: LogLevel = LogLevel.ERROR,
    val exception: Throwable? = null
)
