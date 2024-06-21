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
import kotlin.test.Test
import kotlin.test.assertEquals

class BizRepoUpdateTest {

    private val ownerId = ModelUserId("321")
    private val command = AppCommand.UPDATE
    private val initChatter = ModelBaseChatter(
        id = ModelChatterId("123"),
        message = ModelChatterContentMessage("abc"),
        ownerId = ownerId,
        authorNickName = "",
    )
    private val repo = ChatterRepositoryMock(
        invokeReadChatter = {
            DbChatterResponseOk(
                data = initChatter,
            )
        },
        invokeUpdateChatter = {
            DbChatterResponseOk(
                data = ModelBaseChatter(
                    id = ModelChatterId("123"),
                    message = ModelChatterContentMessage("xyz"),
                    ownerId = ownerId,
                    authorNickName = "",
                )
            )
        }
    )
    private val settings = AppCorSettings(
        repoTest = repo
    )
    private val processor = AppChatterProcessor(settings)

    @Test
    fun repoUpdateSuccessTest() = runTest {
        val chatterToUpdate = ModelChatter(
            id = ModelChatterId("123"),
            message = ModelChatterContentMessage("xyz")
        )
        val ctx = AppContext(
            command = command,
            state = State.NONE,
            workMode = AppWorkMode.TEST,
            chatterRequest = chatterToUpdate,
        )
        processor.exec(ctx)

        assertEquals(State.FINISHING, ctx.state)
        assertEquals(chatterToUpdate.id, ctx.chatterResponse.id)
        assertEquals(chatterToUpdate.message, ctx.chatterResponse.message)
    }

    @Test
    fun repoUpdateNotFoundTest() = repoNotFoundTest(command)
}
