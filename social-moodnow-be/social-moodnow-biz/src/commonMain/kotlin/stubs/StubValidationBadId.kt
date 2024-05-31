package ru.otus.otuskotlin.social.moodnow.biz.stubs

import ru.otus.otuskotlin.social.moodnow.cor.ICorChainDsl
import ru.otus.otuskotlin.social.moodnow.cor.worker
import ru.otus.otuskotlin.social.moodnow.common.AppContext
import ru.otus.otuskotlin.social.moodnow.common.helpers.fail
import ru.otus.otuskotlin.social.moodnow.common.models.ModelError
import ru.otus.otuskotlin.social.moodnow.common.enums.State
import ru.otus.otuskotlin.social.moodnow.common.stubs.AppStubs

fun ICorChainDsl<AppContext>.stubValidationBadId(title: String) = worker {
    this.title = title
    this.description = """
        Кейс ошибки валидации для идентификатора
    """.trimIndent()
    on { stubCase == AppStubs.BAD_ID && state == State.PROCESSING }
    handle {
        fail(
            ModelError(
                group = "validation",
                code = "validation-id",
                field = "id",
                message = "Wrong id field"
            )
        )
    }
}
