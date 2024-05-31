package ru.otus.otuskotlin.social.moodnow.biz.stubs

import ru.otus.otuskotlin.social.moodnow.cor.ICorChainDsl
import ru.otus.otuskotlin.social.moodnow.cor.worker
import ru.otus.otuskotlin.social.moodnow.common.AppContext
import ru.otus.otuskotlin.social.moodnow.common.helpers.fail
import ru.otus.otuskotlin.social.moodnow.common.models.ModelError
import ru.otus.otuskotlin.social.moodnow.common.enums.State

fun ICorChainDsl<AppContext>.stubNoCase(title: String) = worker {
    this.title = title
    this.description = """
        Валидируем ситуацию, когда запрошен кейс, который не поддерживается в стабах
    """.trimIndent()
    on { state == State.PROCESSING }
    handle {
        fail(
            ModelError(
                code = "validation",
                field = "stub",
                group = "validation",
                message = "Wrong stub case is requested"
            )
        )
    }
}
