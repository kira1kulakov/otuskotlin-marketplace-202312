package ru.otus.otuskotlin.social.moodnow.biz.repo

import ru.otus.otuskotlin.social.moodnow.common.AppContext
import ru.otus.otuskotlin.social.moodnow.common.helpers.fail
import ru.otus.otuskotlin.social.moodnow.common.enums.State
import ru.otus.otuskotlin.social.moodnow.common.repo.DbChatterIdRequest
import ru.otus.otuskotlin.social.moodnow.common.repo.DbChatterResponseErr
import ru.otus.otuskotlin.social.moodnow.common.repo.DbChatterResponseErrWithData
import ru.otus.otuskotlin.social.moodnow.common.repo.DbChatterResponseOk
import ru.otus.otuskotlin.social.moodnow.cor.ICorChainDsl
import ru.otus.otuskotlin.social.moodnow.cor.worker

fun ICorChainDsl<AppContext>.repoRead(title: String) = worker {
    this.title = title
    description = "Чтение объявления из БД"
    on { state == State.PROCESSING }
    handle {
        val request = DbChatterIdRequest(chatterValidated)
        when(val result = chatterRepo.readChatter(request)) {
            is DbChatterResponseOk -> chatterRepoRead = result.data
            is DbChatterResponseErr -> fail(result.errors)
            is DbChatterResponseErrWithData -> {
                fail(result.errors)
                chatterRepoRead = result.data
            }
        }
    }
}
