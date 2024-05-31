package ru.otus.otuskotlin.social.moodnow.biz.general

import ru.otus.otuskotlin.social.moodnow.common.AppContext
import ru.otus.otuskotlin.social.moodnow.common.enums.State
import ru.otus.otuskotlin.social.moodnow.cor.ICorChainDsl
import ru.otus.otuskotlin.social.moodnow.cor.chain

fun ICorChainDsl<AppContext>.stubs(title: String, block: ICorChainDsl<AppContext>.() -> Unit) = chain {
    block()
    this.title = title
    on { state == State.PROCESSING }
}
