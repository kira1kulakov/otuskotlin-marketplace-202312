package ru.otus.otuskotlin.social.moodnow.biz.repo

import kotlinx.coroutines.test.runTest
import ru.otus.otuskotlin.social.moodnow.biz.repo.repoNotFoundTest
import ru.otus.otuskotlin.social.moodnow.backend.repo.tests.ChatterRepositoryMock
import ru.otus.otuskotlin.social.moodnow.biz.AppChatterProcessor
import ru.otus.otuskotlin.social.moodnow.common.AppContext
import ru.otus.otuskotlin.social.moodnow.common.AppCorSettings
import ru.otus.otuskotlin.social.moodnow.common.enums.*
import ru.otus.otuskotlin.social.moodnow.common.models.*
import ru.otus.otuskotlin.social.moodnow.common.repo.DbChatterResponseOk
import ru.otus.otuskotlin.social.moodnow.common.repo.DbChatterResponseErr
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class BizRepoDeleteTest {

    private val ownerId = ModelUserId("321")
    private val message = ModelChatterContentMessage("abc")
    private val command = AppCommand.DELETE
    private val initChatter = ModelBaseChatter(
        id = ModelChatterId("123"),
        message = message,
        ownerId = ownerId,
        authorNickName = "",
    )
    private val repo = ChatterRepositoryMock(
        invokeReadChatter = {
            DbChatterResponseOk(
                data = initChatter,
            )
        },
        invokeDeleteChatter = {
            if (it.id == initChatter.id)
                DbChatterResponseOk(
                    data = initChatter
                )
            else DbChatterResponseErr()
        }
    )
    private val settings by lazy {
        AppCorSettings(
            repoTest = repo
        )
    }
    private val processor = AppChatterProcessor(settings)

    @Test
    fun repoDeleteSuccessTest() = runTest {
        val chatterToUpdate = ModelChatter(
            id = ModelChatterId("123"),
        )
        val ctx = AppContext(
            command = command,
            state = State.NONE,
            workMode = AppWorkMode.TEST,
            chatterRequest = chatterToUpdate,
        )
        processor.exec(ctx)

        assertEquals(State.FINISHING, ctx.state)
        assertTrue { ctx.errors.isEmpty() }
        assertEquals(initChatter.id, ctx.chatterResponse.id)
        assertEquals(initChatter.message, ctx.chatterResponse.message)
        assertEquals(initChatter.ownerId, ctx.chatterResponse.ownerId)
        assertEquals(initChatter.authorNickName, ctx.chatterResponse.authorNickName)
    }

    @Test
    fun repoDeleteNotFoundTest() = repoNotFoundTest(command)
}
