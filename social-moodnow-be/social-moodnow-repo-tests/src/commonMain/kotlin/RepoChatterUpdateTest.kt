package ru.otus.otuskotlin.social.moodnow.backend.repo.tests

import ru.otus.otuskotlin.social.moodnow.common.models.*
import ru.otus.otuskotlin.social.moodnow.common.repo.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

abstract class RepoChatterUpdateTest {
    abstract val repo: IRepoChatter
    protected open val updateSucc = initObjects[0]
    protected val updateIdNotFound = ModelChatterId("chatter-repo-update-not-found")

    private val reqUpdateSucc by lazy {
        ModelBaseChatter(
            id = updateSucc.id,
            message = ModelChatterContentMessage("update object"),
        )
    }
    private val reqUpdateNotFound = ModelBaseChatter(
        id = updateIdNotFound,
        message = ModelChatterContentMessage("update object not found"),
    )

    @Test
    fun updateSuccess() = runRepoTest {
        val result = repo.updateChatter(DbChatterRequest(reqUpdateSucc))
        assertIs<DbChatterResponseOk>(result)
        assertEquals(reqUpdateSucc.id, result.data.id)
        assertEquals(reqUpdateSucc.message, result.data.message)
    }

    @Test
    fun updateNotFound() = runRepoTest {
        val result = repo.updateChatter(DbChatterRequest(reqUpdateNotFound))
        assertIs<DbChatterResponseErr>(result)
        val error = result.errors.find { it.code == "repo-not-found" }
        assertEquals("id", error?.field)
    }

    companion object : BaseInitChatters("update") {
        override val initObjects: List<ModelBaseChatter> = listOf(
            createInitTestModel("update"),
        )
    }
}
