package ru.otus.otuskotlin.social.moodnow.mappers.v1

import ru.otus.otuskotlin.social.moodnow.api.v1.models.ChatterCreateObject
import ru.otus.otuskotlin.social.moodnow.api.v1.models.ChatterDeleteObject
import ru.otus.otuskotlin.social.moodnow.api.v1.models.ChatterReadObject
import ru.otus.otuskotlin.social.moodnow.api.v1.models.ChatterUpdateObject
import ru.otus.otuskotlin.social.moodnow.common.models.ModelBaseChatter
import ru.otus.otuskotlin.social.moodnow.common.models.ModelChatterId

fun ModelBaseChatter.toTransportCreate() = ChatterCreateObject(
    message = message.asString(),
)

fun ModelBaseChatter.toTransportRead() = ChatterReadObject(
    id = id.takeIf { it != ModelChatterId.NONE }?.asString(),
)

fun ModelBaseChatter.toTransportUpdate() = ChatterUpdateObject(
    id = id.takeIf { it != ModelChatterId.NONE }?.asString(),
    message = message.asString(),
)

fun ModelBaseChatter.toTransportDelete() = ChatterDeleteObject(
    id = id.takeIf { it != ModelChatterId.NONE }?.asString(),
)
