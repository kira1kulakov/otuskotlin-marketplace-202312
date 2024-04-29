package ru.otus.otuskotlin.social.moodnow.mappers.v1

import ru.otus.otuskotlin.social.moodnow.api.v1.models.*
import ru.otus.otuskotlin.social.moodnow.common.AppContext
import ru.otus.otuskotlin.social.moodnow.common.enums.AppCommand
import ru.otus.otuskotlin.social.moodnow.common.models.ModelChatter
import ru.otus.otuskotlin.social.moodnow.common.models.ModelChatterContentMessage
import ru.otus.otuskotlin.social.moodnow.common.models.ModelChatterId
import ru.otus.otuskotlin.social.moodnow.common.models.ModelUserId
import ru.otus.otuskotlin.social.moodnow.mappers.exceptions.UnknownRequestClass

fun AppContext.fromTransport(request: IChatterRequest) = when (request) {
    is ChatterCreateRequest -> fromTransport(request)
    is ChatterReadRequest -> fromTransport(request)
    is ChatterUpdateRequest -> fromTransport(request)
    is ChatterDeleteRequest -> fromTransport(request)
    else -> throw UnknownRequestClass(request.javaClass)
}


fun AppContext.fromTransport(request: ChatterCreateRequest) {
    command = AppCommand.CREATE
    chatterRequest = request.chatter.toInternal()

}

private fun ChatterCreateObject?.toInternal(): ModelChatter = ModelChatter(
    message = this?.message?.toModelModelChatterContentMessage() ?: ModelChatterContentMessage.NONE
)

fun AppContext.fromTransport(request: ChatterReadRequest) {
    command = AppCommand.CREATE
    chatterRequest = request.chatter.toInternal()

}

private fun ChatterReadObject?.toInternal(): ModelChatter = ModelChatter(
    id = this?.id?.toModelChatterId() ?: ModelChatterId.NONE
)


fun AppContext.fromTransport(request: ChatterUpdateRequest) {
    command = AppCommand.CREATE
    chatterRequest = request.chatter.toInternal()

}

private fun ChatterUpdateObject?.toInternal(): ModelChatter = ModelChatter(
    id = this?.id?.toModelChatterId() ?: ModelChatterId.NONE,
    message = this?.message?.toModelModelChatterContentMessage() ?: ModelChatterContentMessage.NONE
)

fun AppContext.fromTransport(request: ChatterDeleteRequest) {
    command = AppCommand.CREATE
    chatterRequest = request.chatter.toInternal()

}

private fun ChatterDeleteObject?.toInternal(): ModelChatter = ModelChatter(
    id = this?.id?.toModelChatterId() ?: ModelChatterId.NONE
)

private fun String?.toModelChatterId() = this?.let { ModelChatterId(it) } ?: ModelChatterId.NONE

private fun String?.toModelUserId() = this?.let { ModelUserId(it) } ?: ModelUserId.NONE

private fun String?.toModelModelChatterContentMessage() =
    this?.let { ModelChatterContentMessage(it) } ?: ModelChatterContentMessage.NONE
