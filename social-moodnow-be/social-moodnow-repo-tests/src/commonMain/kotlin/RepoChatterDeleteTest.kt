package ru.otus.otuskotlin.social.moodnow.backend.repo.tests

import ru.otus.otuskotlin.social.moodnow.common.models.ModelBaseChatter
import ru.otus.otuskotlin.social.moodnow.common.models.ModelChatterId
import ru.otus.otuskotlin.social.moodnow.common.repo.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertNotNull

abstract class RepoChatterDeleteTest {
    abstract val repo: IRepoChatter
    protected open val deleteSucc = initObjects[0]
    protected open val notFoundId = ModelChatterId("chatter-repo-delete-notFound")

    @Test
    fun deleteSuccess() = runRepoTest {
        val result = repo.deleteChatter(DbChatterIdRequest(deleteSucc.id))
        assertIs<DbChatterResponseOk>(result)
        assertEquals(deleteSucc.message, result.data.message)
    }

    @Test
    fun deleteNotFound() = runRepoTest {
        val result = repo.readChatter(DbChatterIdRequest(notFoundId))

        assertIs<DbChatterResponseErr>(result)
        val error = result.errors.find { it.code == "repo-not-found" }
        assertNotNull(error)
    }

    companion object : BaseInitChatters("delete") {
        override val initObjects: List<ModelBaseChatter> = listOf(
            createInitTestModel("delete"),
        )
    }
}
