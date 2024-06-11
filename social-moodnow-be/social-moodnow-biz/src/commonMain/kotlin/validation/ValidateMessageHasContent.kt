package ru.otus.otuskotlin.social.moodnow.biz.validation

import ru.otus.otuskotlin.social.moodnow.cor.ICorChainDsl
import ru.otus.otuskotlin.social.moodnow.cor.worker
import ru.otus.otuskotlin.social.moodnow.common.helpers.errorValidation
import ru.otus.otuskotlin.social.moodnow.common.AppContext
import ru.otus.otuskotlin.social.moodnow.common.helpers.fail

fun ICorChainDsl<AppContext>.validateMessageHasContent(title: String) = worker {
    this.title = title
    this.description = """
        Проверяем, что у нас есть какие-то слова в заголовке.
        Отказываем в публикации заголовков, в которых только бессмысленные символы типа %^&^$^%#^))&^*&%^^&
    """.trimIndent()
    val regExp = Regex("\\p{L}")
    on { chatterValidating.message.asString().isNotEmpty() && !chatterValidating.message.asString().contains(regExp) }
    handle {
        fail(
            errorValidation(
                field = "message",
                violationCode = "noContent",
                description = "field must contain letters"
            )
        )
    }
}
