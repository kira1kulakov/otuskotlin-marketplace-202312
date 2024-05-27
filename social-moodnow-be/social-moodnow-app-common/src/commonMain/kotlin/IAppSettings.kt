package ru.otus.otuskotlin.social.moodnow.app.common

import ru.otus.otuskotlin.social.moodnow.biz.AppChatterProcessor
import ru.otus.otuskotlin.social.moodnow.common.AppCorSettings

interface IAppSettings {
    val processor: AppChatterProcessor
    val corSettings: AppCorSettings
}