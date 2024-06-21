package ru.otus.otuskotlin.social.moodnow.biz.repo

import ru.otus.otuskotlin.social.moodnow.common.AppContext
import ru.otus.otuskotlin.social.moodnow.common.helpers.fail
import ru.otus.otuskotlin.social.moodnow.common.enums.State
import ru.otus.otuskotlin.social.moodnow.common.repo.DbChatterRequest
import ru.otus.otuskotlin.social.moodnow.common.repo.DbChatterResponseErr
import ru.otus.otuskotlin.social.moodnow.common.repo.DbChatterResponseErrWithData
import ru.otus.otuskotlin.social.moodnow.common.repo.DbChatterResponseOk
import ru.otus.otuskotlin.social.moodnow.cor.ICorChainDsl
import ru.otus.otuskotlin.social.moodnow.cor.worker

fun ICorChainDsl<AppContext>.repoUpdate(title: String) = worker {
    this.title = title
    on { state == State.PROCESSING }
    handle {
        val request = DbChatterRequest(chatterRepoPrepare)
        when(val result = chatterRepo.updateChatter(request)) {
            is DbChatterResponseOk -> chatterRepoDone = result.data
            is DbChatterResponseErr -> fail(result.errors)
            is DbChatterResponseErrWithData -> {
                fail(result.errors)
                chatterRepoDone = result.data
            }
        }
    }
}
