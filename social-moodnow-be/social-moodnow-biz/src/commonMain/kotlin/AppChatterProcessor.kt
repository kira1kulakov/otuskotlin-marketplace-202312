package ru.otus.otuskotlin.social.moodnow.biz

import ru.otus.otuskotlin.social.moodnow.biz.general.*
import ru.otus.otuskotlin.social.moodnow.biz.repo.*
import ru.otus.otuskotlin.social.moodnow.biz.stubs.*
import ru.otus.otuskotlin.social.moodnow.biz.validation.*
import ru.otus.otuskotlin.social.moodnow.common.AppContext
import ru.otus.otuskotlin.social.moodnow.common.AppCorSettings
import ru.otus.otuskotlin.social.moodnow.common.enums.AppCommand
import ru.otus.otuskotlin.social.moodnow.common.enums.State
import ru.otus.otuskotlin.social.moodnow.stubs.AppChatterStub
import ru.otus.otuskotlin.social.moodnow.cor.*

@Suppress("unused", "RedundantSuspendModifier")
class AppChatterProcessor(
    private val appCorSettings: AppCorSettings = AppCorSettings.NONE
) {
    suspend fun exec(context: AppContext) = businessChain.exec(context.also { it.corSettings = appCorSettings })

    private val businessChain = rootChain<AppContext> {
        initStatus("Инициализация статуса")
        initRepo("Инициализация репозитория")

        operation("Создание объявления", AppCommand.CREATE) {
            stubs("Обработка стабов") {
                stubCreateSuccess("Имитация успешной обработки", appCorSettings)
                stubValidationBadMessage("Имитация ошибки валидации описания")
                stubDbError("Имитация ошибки работы с БД")
                stubNoCase("Ошибка: запрошенный стаб недопустим")
            }
            validation {
                worker("Копируем поля в chatterValidating") { chatterValidating = chatterRequest.baseCopy() }
                validateMessageNotEmpty("Проверка на непустое сообщение")
                validateMessageHasContent("Проверка на наличие содержания в сообщении")

                finishChatterValidation("Успешное завершение процедуры валидации")
            }
            chain {
                title = "Логика сохранения"
                repoPrepareCreate("Подготовка объекта для сохранения")
                repoCreate("Создание объявления в БД")
            }
            prepareResult("Подготовка ответа")
        }
        operation("Получить объявление", AppCommand.READ) {
            stubs("Обработка стабов") {
                stubReadSuccess("Имитация успешной обработки", appCorSettings)
                stubValidationBadId("Имитация ошибки валидации id")
                stubDbError("Имитация ошибки работы с БД")
                stubNoCase("Ошибка: запрошенный стаб недопустим")
            }
            validation {
                worker("Копируем поля в chatterValidating") { chatterValidating = chatterRequest.baseCopy() }

                finishChatterValidation("Успешное завершение процедуры валидации")
            }
            chain {
                title = "Логика чтения"
                repoRead("Чтение объявления из БД")
                worker {
                    title = "Подготовка ответа для Read"
                    on { state == State.PROCESSING }
                    handle { chatterRepoDone = chatterRepoRead }
                }
            }
            prepareResult("Подготовка ответа")
        }
        operation("Изменить объявление", AppCommand.UPDATE) {
            stubs("Обработка стабов") {
                stubUpdateSuccess("Имитация успешной обработки", appCorSettings)
                stubValidationBadId("Имитация ошибки валидации id")
                stubValidationBadMessage("Имитация ошибки валидации описания")
                stubDbError("Имитация ошибки работы с БД")
                stubNoCase("Ошибка: запрошенный стаб недопустим")
            }
            validation {
                worker("Копируем поля в chatterValidating") { chatterValidating = chatterRequest.baseCopy() }
                validateMessageNotEmpty("Проверка на непустое сообщение")
                validateMessageHasContent("Проверка на наличие содержания в сообщении")

                finishChatterValidation("Успешное завершение процедуры валидации")
            }
            chain {
                title = "Логика сохранения"
                repoRead("Чтение объявления из БД")
                repoPrepareUpdate("Подготовка объекта для обновления")
                repoUpdate("Обновление объявления в БД")
            }
            prepareResult("Подготовка ответа")
        }
        operation("Удалить объявление", AppCommand.DELETE) {
            stubs("Обработка стабов") {
                stubDeleteSuccess("Имитация успешной обработки", appCorSettings)
                stubValidationBadId("Имитация ошибки валидации id")
                stubDbError("Имитация ошибки работы с БД")
                stubNoCase("Ошибка: запрошенный стаб недопустим")
            }
            validation {
                worker("Копируем поля в chatterValidating") { chatterValidating = chatterRequest.baseCopy() }

                finishChatterValidation("Успешное завершение процедуры валидации")
            }
            chain {
                title = "Логика удаления"
                repoRead("Чтение объявления из БД")
                repoPrepareDelete("Подготовка объекта для удаления")
                repoDelete("Удаление объявления из БД")
            }
            prepareResult("Подготовка ответа")
        }

    }.build()
}