package ru.otus.otuskotlin.social.moodnow.biz.validation

import ru.otus.otuskotlin.social.moodnow.common.AppContext
import ru.otus.otuskotlin.social.moodnow.common.enums.State
import ru.otus.otuskotlin.social.moodnow.cor.ICorChainDsl
import ru.otus.otuskotlin.social.moodnow.cor.worker

fun ICorChainDsl<AppContext>.finishChatterValidation(title: String) = worker {
    this.title = title
    on { state == State.PROCESSING }
    handle {
        chatterValidated = chatterValidating
    }
}
