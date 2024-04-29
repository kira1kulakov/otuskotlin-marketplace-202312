package ru.otus.otuskotlin.social.moodnow.mappers.v1

import ru.otus.otuskotlin.social.moodnow.api.v1.models.*
import ru.otus.otuskotlin.social.moodnow.common.AppContext
import ru.otus.otuskotlin.social.moodnow.common.enums.AppCommand
import ru.otus.otuskotlin.social.moodnow.common.enums.State
import ru.otus.otuskotlin.social.moodnow.common.exceptions.UnknownRestCommandException
import ru.otus.otuskotlin.social.moodnow.common.models.ModelBaseChatter
import ru.otus.otuskotlin.social.moodnow.common.models.ModelError

fun AppContext.toTransport(): IChatterResponse = when (val command = command) {
    AppCommand.CREATE -> toTransportCreate()
    AppCommand.READ -> toTransportRead()
    AppCommand.UPDATE -> toTransportUpdate()
    AppCommand.DELETE -> toTransportDelete()
    AppCommand.NONE -> throw UnknownRestCommandException(command)
}

fun AppContext.toTransportCreate() = ChatterCreateResponse(
    result = state.toResult(),
    errors = errors.toTransportErrors(),
    chatter = chatterResponse.toTransportChatter()
)

fun AppContext.toTransportRead() = ChatterReadResponse(
    result = state.toResult(),
    errors = errors.toTransportErrors(),
    chatter = chatterResponse.toTransportChatter()
)

fun AppContext.toTransportUpdate() = ChatterUpdateResponse(
    result = state.toResult(),
    errors = errors.toTransportErrors(),
    chatter = chatterResponse.toTransportChatter()
)

fun AppContext.toTransportDelete() = ChatterDeleteResponse(
    result = state.toResult(),
    errors = errors.toTransportErrors()
)

private fun ModelBaseChatter.toTransportChatter() = BaseChatter(
    id = id.asString(),
    ownerId = ownerId.asString(),
    authorNickName = authorNickName,
    message = message.asString(),
    createdAt = createdAt.toString()
)

private fun List<ModelError>.toTransportErrors(): List<Error>? = this
    .map { it.toTransportAd() }
    .toList()
    .takeIf { it.isNotEmpty() }

private fun ModelError.toTransportAd() = Error(
    code = code.takeIf { it.isNotBlank() },
    group = group.takeIf { it.isNotBlank() },
    field = field.takeIf { it.isNotBlank() },
    message = message.takeIf { it.isNotBlank() },
)

private fun State.toResult(): ResponseResult? = when (this) {
    State.PROCESSING -> ResponseResult.SUCCESS
    State.FAILING -> ResponseResult.ERROR
    State.FINISHING -> ResponseResult.SUCCESS
    State.NONE -> null
}