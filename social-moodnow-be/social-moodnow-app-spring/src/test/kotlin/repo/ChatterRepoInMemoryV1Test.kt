package ru.otus.otuskotlin.social.moodnow.app.spring.repo

import com.ninjasquad.springmockk.MockkBean
import io.mockk.coEvery
import io.mockk.slot
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.web.reactive.server.WebTestClient
import ru.otus.otuskotlin.social.moodnow.app.spring.config.MainConfig
import ru.otus.otuskotlin.social.moodnow.app.spring.controller.ControllerV1
import ru.otus.otuskotlin.social.moodnow.common.repo.*
import ru.otus.otuskotlin.social.moodnow.repo.common.*
import ru.otus.otuskotlin.social.moodnow.repo.inmemory.ChatterRepoInMemory
import ru.otus.otuskotlin.social.moodnow.stubs.AppChatterStub
import kotlin.test.Test

@WebFluxTest(ControllerV1::class, MainConfig::class)
@TestMethodOrder(OrderAnnotation::class)
internal class ChatterRepoInMemoryV1Test : ChatterRepoBaseV1Test() {
    @Autowired
    override lateinit var webClient: WebTestClient

    @MockkBean
    @Qualifier("testRepo")
    lateinit var testRepo: IRepoChatter

    @BeforeEach
    fun tearUp() {
        val slotChatter = slot<DbChatterRequest>()
        val slotId = slot<DbChatterIdRequest>()
        val repo = ChatterRepoInitialized(
            repo = ChatterRepoInMemory(randomUuid = { uuidNew }),
            initObjects = mutableListOf(AppChatterStub.getAnyResponse())
        )
        coEvery { testRepo.createChatter(capture(slotChatter)) } coAnswers { repo.createChatter(slotChatter.captured) }

        coEvery { testRepo.readChatter(capture(slotId)) } coAnswers { repo.readChatter(slotId.captured) }

        coEvery { testRepo.updateChatter(capture(slotChatter)) } coAnswers { repo.updateChatter(slotChatter.captured) }

        coEvery { testRepo.deleteChatter(capture(slotId)) } coAnswers { repo.deleteChatter(slotId.captured) }
    }

    @Test
    @Order(0)
    override fun createChatter() = super.createChatter()

    @Test
    @Order(1)
    override fun readChatter() = super.readChatter()

    @Test
    @Order(2)
    override fun updateChatter() = super.updateChatter()

    @Test
    @Order(3)
    override fun deleteChatter() = super.deleteChatter()
}
