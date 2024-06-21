package ru.otus.otuskotlin.social.moodnow.backend.repo.tests

import kotlinx.coroutines.test.runTest
import ru.otus.otuskotlin.social.moodnow.common.models.*
import ru.otus.otuskotlin.social.moodnow.common.repo.*
import ru.otus.otuskotlin.social.moodnow.stubs.AppChatterStub
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class ChatterRepositoryMockTest {
    private val repo = ChatterRepositoryMock(
        invokeCreateChatter = { DbChatterResponseOk(AppChatterStub.prepareResult { message = ModelChatterContentMessage("create") }) },
        invokeReadChatter = { DbChatterResponseOk(AppChatterStub.prepareResult { message = ModelChatterContentMessage("read") }) },
        invokeUpdateChatter = { DbChatterResponseOk(AppChatterStub.prepareResult { message = ModelChatterContentMessage("update") }) },
        invokeDeleteChatter = { DbChatterResponseOk(AppChatterStub.prepareResult { message = ModelChatterContentMessage("delete") }) },
    )

    @Test
    fun mockCreate() = runTest {
        val result = repo.createChatter(DbChatterRequest(ModelBaseChatter()))
        assertIs<DbChatterResponseOk>(result)
        assertEquals("create", result.data.message.asString())
    }

    @Test
    fun mockRead() = runTest {
        val result = repo.readChatter(DbChatterIdRequest(ModelBaseChatter()))
        assertIs<DbChatterResponseOk>(result)
        assertEquals("read", result.data.message.asString())
    }

    @Test
    fun mockUpdate() = runTest {
        val result = repo.updateChatter(DbChatterRequest(ModelBaseChatter()))
        assertIs<DbChatterResponseOk>(result)
        assertEquals("update", result.data.message.asString())
    }

    @Test
    fun mockDelete() = runTest {
        val result = repo.deleteChatter(DbChatterIdRequest(ModelBaseChatter()))
        assertIs<DbChatterResponseOk>(result)
        assertEquals("delete", result.data.message.asString())
    }
}
