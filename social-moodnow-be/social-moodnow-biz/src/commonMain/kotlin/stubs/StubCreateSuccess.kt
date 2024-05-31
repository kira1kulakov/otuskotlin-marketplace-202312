package ru.otus.otuskotlin.social.moodnow.biz.stubs

import ru.otus.otuskotlin.social.moodnow.cor.ICorChainDsl
import ru.otus.otuskotlin.social.moodnow.cor.worker
import ru.otus.otuskotlin.social.moodnow.common.AppContext
import ru.otus.otuskotlin.social.moodnow.common.AppCorSettings
import ru.otus.otuskotlin.social.moodnow.common.enums.State
import ru.otus.otuskotlin.social.moodnow.stubs.AppChatterStub
import ru.otus.otuskotlin.social.moodnow.logging.common.LogLevel
import ru.otus.otuskotlin.social.moodnow.common.stubs.AppStubs

fun ICorChainDsl<AppContext>.stubCreateSuccess(title: String, corSettings: AppCorSettings) = worker {
    this.title = title
    this.description = """
        Кейс успеха для создания
    """.trimIndent()
    on { stubCase == AppStubs.SUCCESS && state == State.PROCESSING }
    val logger = corSettings.loggerProvider.logger("stubCreateSuccess")
    handle {
        logger.doWithLogging(id = this.chatterId.asString(), LogLevel.DEBUG) {
            state = State.FINISHING
            chatterRequest = AppChatterStub.getAnyRequest()
            chatterResponse = AppChatterStub.getAnyResponse()
        }
    }
}
