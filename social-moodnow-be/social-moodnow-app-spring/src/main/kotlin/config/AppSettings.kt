package ru.otus.otuskotlin.social.moodnow.app.spring.config

import ru.otus.otuskotlin.social.moodnow.app.common.IAppSettings
import ru.otus.otuskotlin.social.moodnow.biz.AppChatterProcessor
import ru.otus.otuskotlin.social.moodnow.common.AppCorSettings

data class AppSettings(
    override val corSettings: AppCorSettings,
    override val processor: AppChatterProcessor
) : IAppSettings