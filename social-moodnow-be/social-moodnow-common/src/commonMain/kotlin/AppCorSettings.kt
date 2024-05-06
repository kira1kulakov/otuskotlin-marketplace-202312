package ru.otus.otuskotlin.social.moodnow.common

import ru.otus.otuskotlin.social.moodnow.logging.common.MpLoggerProvider

data class AppCorSettings(
    val loggerProvider: MpLoggerProvider = MpLoggerProvider(),
) {

    companion object {
        val NONE = AppCorSettings()
    }
}