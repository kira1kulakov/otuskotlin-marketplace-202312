package ru.otus.otuskotlin.social.moodnow.biz.exceptions

import ru.otus.otuskotlin.social.moodnow.common.enums.AppWorkMode

class DbNotConfiguredException(val workMode: AppWorkMode) :
    Exception(
        "Database is not configured properly for workmode $workMode"
    )