package ru.otus.otuskotlin.social.moodnow.biz.repo

import ru.otus.otuskotlin.social.moodnow.common.AppContext
import ru.otus.otuskotlin.social.moodnow.common.enums.State
import ru.otus.otuskotlin.social.moodnow.cor.ICorChainDsl
import ru.otus.otuskotlin.social.moodnow.cor.worker

fun ICorChainDsl<AppContext>.repoPrepareCreate(title: String) = worker {
    this.title = title
    description = "Подготовка объекта к сохранению в базе данных"
    on { state == State.PROCESSING }
    handle {
        chatterRepoRead = chatterValidated.deepCopy()
        chatterRepoPrepare = chatterRepoRead
    }
}
