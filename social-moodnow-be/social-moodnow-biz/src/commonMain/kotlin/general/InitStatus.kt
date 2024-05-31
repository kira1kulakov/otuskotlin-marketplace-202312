package ru.otus.otuskotlin.social.moodnow.biz.general

import ru.otus.otuskotlin.social.moodnow.cor.ICorChainDsl
import ru.otus.otuskotlin.social.moodnow.cor.worker
import ru.otus.otuskotlin.social.moodnow.common.AppContext
import ru.otus.otuskotlin.social.moodnow.common.enums.State

fun ICorChainDsl<AppContext>.initStatus(title: String) = worker() {
    this.title = title
    this.description = """
        Этот обработчик устанавливает стартовый статус обработки. Запускается только в случае не заданного статуса.
    """.trimIndent()
    on { state == State.NONE }
    handle { state = State.PROCESSING }
}
