package ru.otus.otuskotlin.social.moodnow.biz.repo

import kotlinx.coroutines.test.runTest
import ru.otus.otuskotlin.social.moodnow.backend.repo.tests.ChatterRepositoryMock
import ru.otus.otuskotlin.social.moodnow.biz.AppChatterProcessor
import ru.otus.otuskotlin.social.moodnow.common.AppContext
import ru.otus.otuskotlin.social.moodnow.common.AppCorSettings
import ru.otus.otuskotlin.social.moodnow.common.enums.*
import ru.otus.otuskotlin.social.moodnow.common.models.*
import ru.otus.otuskotlin.social.moodnow.common.repo.DbChatterResponseOk
import ru.otus.otuskotlin.social.moodnow.common.repo.errorNotFound
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

private val initChatter = ModelBaseChatter(
    id = ModelChatterId("123"),
    message = ModelChatterContentMessage("abc")
)
private val repo = ChatterRepositoryMock(
    invokeReadChatter = {
        if (it.id == initChatter.id) {
            DbChatterResponseOk(
                data = initChatter,
            )
        } else errorNotFound(it.id)
    }
)
private val settings = AppCorSettings(repoTest = repo)
private val processor = AppChatterProcessor(settings)

fun repoNotFoundTest(command: AppCommand) = runTest {
    val ctx = AppContext(
        command = command,
        state = State.NONE,
        workMode = AppWorkMode.TEST,
        chatterRequest = ModelChatter(
            id = ModelChatterId("2"),
            message = ModelChatterContentMessage("xyz")
        ),
    )
    processor.exec(ctx)

    assertEquals(State.FAILING, ctx.state)
    assertEquals(ModelBaseChatter(), ctx.chatterResponse)
    assertEquals(1, ctx.errors.size)
    assertNotNull(ctx.errors.find { it.code == "repo-not-found" }, "Errors must contain not-found")
}
