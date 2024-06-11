package ru.otus.otuskotlin.social.moodnow.biz.validation

import ru.otus.otuskotlin.social.moodnow.cor.ICorChainDsl
import ru.otus.otuskotlin.social.moodnow.cor.worker
import ru.otus.otuskotlin.social.moodnow.common.helpers.errorValidation
import ru.otus.otuskotlin.social.moodnow.common.AppContext
import ru.otus.otuskotlin.social.moodnow.common.helpers.fail

fun ICorChainDsl<AppContext>.validateMessageNotEmpty(title: String) = worker {
    this.title = title
    on { chatterValidating.message.asString().isEmpty() }
    handle {
        fail(
            errorValidation(
                field = "message",
                violationCode = "empty",
                description = "field must not be empty"
            )
        )
    }
}
