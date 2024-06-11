package ru.otus.otuskotlin.social.moodnow.common

import kotlinx.datetime.Instant
import ru.otus.otuskotlin.social.moodnow.common.enums.AppCommand
import ru.otus.otuskotlin.social.moodnow.common.enums.State
import ru.otus.otuskotlin.social.moodnow.common.models.ModelBaseChatter
import ru.otus.otuskotlin.social.moodnow.common.models.ModelChatter
import ru.otus.otuskotlin.social.moodnow.common.models.ModelChatterId
import ru.otus.otuskotlin.social.moodnow.common.models.ModelError
import ru.otus.otuskotlin.social.moodnow.common.stubs.AppStubs
import ru.otus.otuskotlin.social.moodnow.common.enums.AppWorkMode
import ru.otus.otuskotlin.social.moodnow.common.repo.IRepoChatter

data class AppContext(
    var command: AppCommand = AppCommand.NONE,

    var state: State = State.NONE,

    var chatterId: ModelChatterId = ModelChatterId.NONE,

    var chatterRequest: ModelChatter = ModelChatter(),

    var chatterResponse: ModelBaseChatter = ModelBaseChatter(),

    var timeStart: Instant = Instant.NONE,

    val errors: MutableList<ModelError> = mutableListOf(),

    var corSettings: AppCorSettings = AppCorSettings(),

    var stubCase: AppStubs = AppStubs.NONE,

    var workMode: AppWorkMode = AppWorkMode.PROD,

    var chatterValidating: ModelBaseChatter = ModelBaseChatter(),

    var chatterValidated: ModelBaseChatter = ModelBaseChatter(),

    var chatterRepo: IRepoChatter = IRepoChatter.NONE,

    var chatterRepoRead: ModelBaseChatter = ModelBaseChatter(), // То, что прочитали из репозитория

    var chatterRepoPrepare: ModelBaseChatter = ModelBaseChatter(), // То, что готовим для сохранения в БД

    var chatterRepoDone: ModelBaseChatter = ModelBaseChatter(),  // Результат, полученный из БД

)
