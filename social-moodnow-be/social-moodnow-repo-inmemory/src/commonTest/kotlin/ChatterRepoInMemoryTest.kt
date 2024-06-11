import ru.otus.otuskotlin.social.moodnow.backend.repo.tests.*

import ru.otus.otuskotlin.social.moodnow.repo.common.ChatterRepoInitialized
import ru.otus.otuskotlin.social.moodnow.repo.inmemory.ChatterRepoInMemory

class ChatterRepoInMemoryCreateTest : RepoChatterCreateTest() {
    override val repo = ChatterRepoInitialized(
        ChatterRepoInMemory(),
        initObjects = initObjects,
    )
}

class ChatterRepoInMemoryReadTest : RepoChatterReadTest() {
    override val repo = ChatterRepoInitialized(
        ChatterRepoInMemory(),
        initObjects = initObjects,
    )
}

class ChatterRepoInMemoryUpdateTest : RepoChatterUpdateTest() {
    override val repo = ChatterRepoInitialized(
        ChatterRepoInMemory(),
        initObjects = initObjects,
    )
}

class ChatterRepoInMemoryDeleteTest : RepoChatterDeleteTest() {
    override val repo = ChatterRepoInitialized(
        ChatterRepoInMemory(),
        initObjects = initObjects,
    )
}
