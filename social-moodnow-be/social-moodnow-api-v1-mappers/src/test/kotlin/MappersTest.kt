package ru.otus.otuskotlin.social.moodnow.mappers.v1

import kotlinx.datetime.Clock
import ru.otus.otuskotlin.social.moodnow.api.v1.models.ChatterCreateObject
import ru.otus.otuskotlin.social.moodnow.api.v1.models.ChatterCreateRequest
import ru.otus.otuskotlin.social.moodnow.api.v1.models.ChatterCreateResponse
import ru.otus.otuskotlin.social.moodnow.api.v1.models.ResponseResult
import ru.otus.otuskotlin.social.moodnow.common.AppContext
import ru.otus.otuskotlin.social.moodnow.common.enums.AppCommand
import ru.otus.otuskotlin.social.moodnow.common.enums.State
import ru.otus.otuskotlin.social.moodnow.common.models.*
import kotlin.test.Test
import kotlin.test.assertEquals

class MappersTest {

    @Test
    fun fromTransport() {

        val messageText = "Test message"

        val request = ChatterCreateRequest(
            chatter = ChatterCreateObject(
                message = messageText
            )
        )

        val context = AppContext()
        context.fromTransport(request)

        assertEquals(AppCommand.CREATE, context.command)
        assertEquals(State.NONE, context.state)
        assertEquals(ModelChatterId.NONE, context.chatterId)
        assertEquals(messageText, context.chatterRequest.message.asString())
        assertEquals(0, context.errors.size)
    }

    @Test
    fun toTransport() {

        val id = "1"
        val userId = "1"
        val messageText = "Test message"
        val createdAt = Clock.System.now()

        val context = AppContext(
            command = AppCommand.CREATE,
            state = State.FAILING,
            chatterId = ModelChatterId(id),
            timeStart = createdAt,
            chatterResponse = ModelBaseChatter(
                id = ModelChatterId(id),
                ownerId = ModelUserId(userId),
                authorNickName = "User 1",
                message = ModelChatterContentMessage(messageText),
                createdAt = createdAt
            ),
            errors = mutableListOf(
                ModelError(
                    code = "error",
                    group = "response",
                    field = "id",
                    message = "failure"
                )
            )
        )

        val response = context.toTransport() as ChatterCreateResponse

        assertEquals(ResponseResult.ERROR, response.result)

        assertEquals(id, response.chatter?.id)
        assertEquals(userId, response.chatter?.ownerId)
        assertEquals(messageText, response.chatter?.message)
        assertEquals("User 1", response.chatter?.authorNickName)
        assertEquals(createdAt.toString(), response.chatter?.createdAt)

        assertEquals("error", response.errors?.firstOrNull()?.code)
        assertEquals("response", response.errors?.firstOrNull()?.group)
        assertEquals("id", response.errors?.firstOrNull()?.field)
        assertEquals("failure", response.errors?.firstOrNull()?.message)
    }
}