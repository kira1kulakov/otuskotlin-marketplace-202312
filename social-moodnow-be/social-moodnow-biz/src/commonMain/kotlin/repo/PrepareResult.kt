package ru.otus.otuskotlin.social.moodnow.biz.repo

import ru.otus.otuskotlin.social.moodnow.common.AppContext
import ru.otus.otuskotlin.social.moodnow.common.enums.State
import ru.otus.otuskotlin.social.moodnow.common.enums.AppWorkMode
import ru.otus.otuskotlin.social.moodnow.cor.ICorChainDsl
import ru.otus.otuskotlin.social.moodnow.cor.worker

fun ICorChainDsl<AppContext>.prepareResult(title: String) = worker {
    this.title = title
    description = "Подготовка данных для ответа клиенту на запрос"
    on { workMode != AppWorkMode.STUB }
    handle {
        chatterResponse = chatterRepoDone
        state = when (val st = state) {
            State.PROCESSING -> State.FINISHING
            else -> st
        }
    }
}
