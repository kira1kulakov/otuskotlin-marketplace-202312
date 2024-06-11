package ru.otus.otuskotlin.social.moodnow.biz

import ru.otus.otuskotlin.social.moodnow.biz.general.initStatus
import ru.otus.otuskotlin.social.moodnow.biz.general.operation
import ru.otus.otuskotlin.social.moodnow.biz.general.stubs
import ru.otus.otuskotlin.social.moodnow.biz.stubs.*
import ru.otus.otuskotlin.social.moodnow.common.AppContext
import ru.otus.otuskotlin.social.moodnow.common.AppCorSettings
import ru.otus.otuskotlin.social.moodnow.common.enums.AppCommand
import ru.otus.otuskotlin.social.moodnow.common.enums.State
import ru.otus.otuskotlin.social.moodnow.stubs.AppChatterStub
import ru.otus.otuskotlin.social.moodnow.cor.rootChain

@Suppress("unused", "RedundantSuspendModifier")
class AppChatterProcessor(
    private val appCorSettings: AppCorSettings = AppCorSettings.NONE
) {
    suspend fun exec(context: AppContext) = businessChain.exec(context.also { it.corSettings = appCorSettings })

    private val businessChain = rootChain<AppContext> {
        initStatus("Инициализация статуса")

        operation("Создание объявления", AppCommand.CREATE) {
            stubs("Обработка стабов") {
                stubCreateSuccess("Имитация успешной обработки", appCorSettings)
                stubValidationBadMessage("Имитация ошибки валидации описания")
                stubDbError("Имитация ошибки работы с БД")
                stubNoCase("Ошибка: запрошенный стаб недопустим")
            }
        }
        operation("Получить объявление", AppCommand.READ) {
            stubs("Обработка стабов") {
                stubReadSuccess("Имитация успешной обработки", appCorSettings)
                stubValidationBadId("Имитация ошибки валидации id")
                stubDbError("Имитация ошибки работы с БД")
                stubNoCase("Ошибка: запрошенный стаб недопустим")
            }
        }
        operation("Изменить объявление", AppCommand.UPDATE) {
            stubs("Обработка стабов") {
                stubUpdateSuccess("Имитация успешной обработки", appCorSettings)
                stubValidationBadId("Имитация ошибки валидации id")
                stubValidationBadMessage("Имитация ошибки валидации описания")
                stubDbError("Имитация ошибки работы с БД")
                stubNoCase("Ошибка: запрошенный стаб недопустим")
            }
        }
        operation("Удалить объявление", AppCommand.DELETE) {
            stubs("Обработка стабов") {
                stubDeleteSuccess("Имитация успешной обработки", appCorSettings)
                stubValidationBadId("Имитация ошибки валидации id")
                stubDbError("Имитация ошибки работы с БД")
                stubNoCase("Ошибка: запрошенный стаб недопустим")
            }
        }

    }.build()
}