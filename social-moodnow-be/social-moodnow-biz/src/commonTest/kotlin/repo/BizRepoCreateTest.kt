package ru.otus.otuskotlin.social.moodnow.biz.repo

import kotlinx.coroutines.test.runTest
import ru.otus.otuskotlin.social.moodnow.backend.repo.tests.ChatterRepositoryMock
import ru.otus.otuskotlin.social.moodnow.biz.AppChatterProcessor
import ru.otus.otuskotlin.social.moodnow.common.AppContext
import ru.otus.otuskotlin.social.moodnow.common.AppCorSettings
import ru.otus.otuskotlin.social.moodnow.common.enums.*
import ru.otus.otuskotlin.social.moodnow.common.models.*
import ru.otus.otuskotlin.social.moodnow.common.repo.DbChatterResponseOk
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class BizRepoCreateTest {

    private val ownerId = ModelUserId("321")
    private val command = AppCommand.CREATE
    private val uuid = "00000000-0000-0000-0000-000000000000"
    private val repo = ChatterRepositoryMock(
        invokeCreateChatter = {
            DbChatterResponseOk(
                data = ModelBaseChatter(
                    id = ModelChatterId(uuid),
                    message = it.chatter.message,
                    ownerId = ownerId,
                    authorNickName = it.chatter.authorNickName,
                )
            )
        }
    )
    private val settings = AppCorSettings(
        repoTest = repo
    )
    private val processor = AppChatterProcessor(settings)

    @Test
    fun repoCreateSuccessTest() = runTest {
        val ctx = AppContext(
            command = command,
            state = State.NONE,
            workMode = AppWorkMode.TEST,
            chatterRequest = ModelChatter(
                message = ModelChatterContentMessage("abc"),
            ),
        )
        processor.exec(ctx)

        assertEquals(State.FINISHING, ctx.state)
        assertNotEquals(ModelChatterId.NONE, ctx.chatterResponse.id)
        assertEquals("abc", ctx.chatterResponse.message.asString())
    }
}
