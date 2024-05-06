package ru.otus.otuskotlin.social.moodnow.common

import kotlinx.datetime.Instant
import ru.otus.otuskotlin.social.moodnow.common.enums.AppCommand
import ru.otus.otuskotlin.social.moodnow.common.enums.State
import ru.otus.otuskotlin.social.moodnow.common.models.ModelBaseChatter
import ru.otus.otuskotlin.social.moodnow.common.models.ModelChatter
import ru.otus.otuskotlin.social.moodnow.common.models.ModelChatterId
import ru.otus.otuskotlin.social.moodnow.common.models.ModelError

data class AppContext(
    var command: AppCommand = AppCommand.NONE,

    var state: State = State.NONE,

    var chatterId: ModelChatterId = ModelChatterId.NONE,

    var chatterRequest: ModelChatter = ModelChatter(),

    var chatterResponse: ModelBaseChatter = ModelBaseChatter(),

    var timeStart: Instant = Instant.NONE,

    val errors: MutableList<ModelError> = mutableListOf(),
)