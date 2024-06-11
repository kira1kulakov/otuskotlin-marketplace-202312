package ru.otus.otuskotlin.social.moodnow.biz.stubs

import ru.otus.otuskotlin.social.moodnow.common.AppContext
import ru.otus.otuskotlin.social.moodnow.common.enums.State
import ru.otus.otuskotlin.social.moodnow.cor.ICorChainDsl
import ru.otus.otuskotlin.social.moodnow.cor.chain
import ru.otus.otuskotlin.social.moodnow.common.enums.AppWorkMode

fun ICorChainDsl<AppContext>.stubs(title: String, block: ICorChainDsl<AppContext>.() -> Unit) = chain {
    block()
    this.title = title
    on { workMode == AppWorkMode.STUB && state == State.PROCESSING }
}
