package ru.otus.otuskotlin.social.moodnow.biz

import ru.otus.otuskotlin.social.moodnow.common.AppContext
import ru.otus.otuskotlin.social.moodnow.common.AppCorSettings
import ru.otus.otuskotlin.social.moodnow.common.enums.State
import ru.otus.otuskotlin.social.moodnow.stubs.AppChatterStub

@Suppress("unused", "RedundantSuspendModifier")
class AppChatterProcessor(val appCorSettings: AppCorSettings) {
    suspend fun exec(context: AppContext) {
        context.chatterRequest = AppChatterStub.getAnyRequest()
        context.chatterResponse = AppChatterStub.getAnyResponse()
        context.state = State.PROCESSING
    }
}