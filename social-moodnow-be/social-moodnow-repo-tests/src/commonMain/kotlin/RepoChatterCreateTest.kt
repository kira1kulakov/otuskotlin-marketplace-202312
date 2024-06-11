package ru.otus.otuskotlin.social.moodnow.backend.repo.tests

import ru.otus.otuskotlin.social.moodnow.common.models.*
import ru.otus.otuskotlin.social.moodnow.common.repo.DbChatterRequest
import ru.otus.otuskotlin.social.moodnow.common.repo.DbChatterResponseOk
import ru.otus.otuskotlin.social.moodnow.repo.common.IRepoChatterInitializable
import kotlin.test.*


abstract class RepoChatterCreateTest {
    abstract val repo: IRepoChatterInitializable

    private val createObj = ModelBaseChatter(
        message = ModelChatterContentMessage("create object"),
    )

    @Test
    fun createSuccess() = runRepoTest {
        val result = repo.createChatter(DbChatterRequest(createObj))
        assertIs<DbChatterResponseOk>(result)

        val expected = createObj.copy(id = result.data.id)
        assertEquals(expected.message, result.data.message)
        assertNotEquals(ModelChatterId.NONE, result.data.id)
    }

    companion object : BaseInitChatters("create") {
        override val initObjects: List<ModelBaseChatter> = emptyList()
    }
}
