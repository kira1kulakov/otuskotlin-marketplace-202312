package ru.otus.otuskotlin.social.moodnow.biz.validation

import ru.otus.otuskotlin.social.moodnow.common.AppContext
import ru.otus.otuskotlin.social.moodnow.common.enums.State
import ru.otus.otuskotlin.social.moodnow.cor.ICorChainDsl
import ru.otus.otuskotlin.social.moodnow.cor.chain

fun ICorChainDsl<AppContext>.validation(block: ICorChainDsl<AppContext>.() -> Unit) = chain {
    block()
    title = "Валидация"

    on { state == State.PROCESSING }
}
