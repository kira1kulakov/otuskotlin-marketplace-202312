package ru.otus.otuskotlin.social.moodnow.biz.stub

import kotlinx.coroutines.test.runTest
import ru.otus.otuskotlin.social.moodnow.biz.AppChatterProcessor
import ru.otus.otuskotlin.social.moodnow.common.AppContext
import ru.otus.otuskotlin.social.moodnow.common.enums.*
import ru.otus.otuskotlin.social.moodnow.common.models.*
import ru.otus.otuskotlin.social.moodnow.common.stubs.AppStubs
import ru.otus.otuskotlin.social.moodnow.stubs.AppChatterStub
import kotlin.test.Test
import kotlin.test.assertEquals

class CreateStubTest {

    private val processor = AppChatterProcessor()

    private val id = "00000000-0000-0000-0000-000000000000"
    private val message = "Default text of message"

    private val ownerId = ""
    private val nickName = ""

    @Test
    fun create() = runTest {

        val ctx = AppContext(
            command = AppCommand.CREATE,
            state = State.NONE,
            workMode = AppWorkMode.STUB,
            stubCase = AppStubs.SUCCESS,
            chatterRequest = ModelChatter(
                id = ModelChatterId(id),
                message = ModelChatterContentMessage(message)
            )
        )
        processor.exec(ctx)

        assertEquals(AppChatterStub.getAnyRequest().id, ctx.chatterRequest.id)
        assertEquals(AppChatterStub.getAnyRequest().message, ctx.chatterRequest.message)
        assertEquals(AppChatterStub.getAnyResponse().id, ctx.chatterResponse.id)
        assertEquals(AppChatterStub.getAnyResponse().message, ctx.chatterResponse.message)
        assertEquals(AppChatterStub.getAnyResponse().ownerId, ModelUserId(ownerId))
        assertEquals(AppChatterStub.getAnyResponse().authorNickName, nickName)
    }

    @Test
    fun badMessage() = runTest {

        val ctx = AppContext(
            command = AppCommand.CREATE,
            state = State.NONE,
            workMode = AppWorkMode.STUB,
            stubCase = AppStubs.BAD_MESSAGE,
            chatterRequest = ModelChatter(
                id = ModelChatterId(id),
                message = ModelChatterContentMessage(message)
            )
        )
        processor.exec(ctx)

        assertEquals(ModelBaseChatter().id, ctx.chatterResponse.id)
        assertEquals(ModelBaseChatter().message, ctx.chatterResponse.message)
        assertEquals(ModelBaseChatter().ownerId, ctx.chatterResponse.ownerId)
        assertEquals("message", ctx.errors.firstOrNull()?.field)
        assertEquals("validation", ctx.errors.firstOrNull()?.group)
    }

    @Test
    fun databaseError() = runTest {

        val ctx = AppContext(
            command = AppCommand.CREATE,
            state = State.NONE,
            workMode = AppWorkMode.STUB,
            stubCase = AppStubs.DB_ERROR,
            chatterRequest = ModelChatter(
                id = ModelChatterId(id),
                message = ModelChatterContentMessage(message)
            )
        )
        processor.exec(ctx)

        assertEquals(ModelBaseChatter().id, ctx.chatterResponse.id)
        assertEquals(ModelBaseChatter().message, ctx.chatterResponse.message)
        assertEquals(ModelBaseChatter().ownerId, ctx.chatterResponse.ownerId)
        assertEquals("internal", ctx.errors.firstOrNull()?.group)
    }

    @Test
    fun badNoCase() = runTest {

        val ctx = AppContext(
            command = AppCommand.CREATE,
            state = State.NONE,
            workMode = AppWorkMode.STUB,
            stubCase = AppStubs.BAD_ID,
            chatterRequest = ModelChatter(
                id = ModelChatterId(id),
                message = ModelChatterContentMessage(message)
            )
        )
        processor.exec(ctx)

        assertEquals(ModelBaseChatter().id, ctx.chatterResponse.id)
        assertEquals(ModelBaseChatter().message, ctx.chatterResponse.message)
        assertEquals(ModelBaseChatter().ownerId, ctx.chatterResponse.ownerId)
        assertEquals("stub", ctx.errors.firstOrNull()?.field)
        assertEquals("validation", ctx.errors.firstOrNull()?.group)
    }
}