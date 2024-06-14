package ru.otus.otuskotlin.social.moodnow.app.spring.stub

import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters
import ru.otus.otuskotlin.social.moodnow.api.v1.models.*
import ru.otus.otuskotlin.social.moodnow.app.spring.config.MainConfig
import ru.otus.otuskotlin.social.moodnow.app.spring.controller.ControllerV1
import ru.otus.otuskotlin.social.moodnow.biz.AppChatterProcessor
import ru.otus.otuskotlin.social.moodnow.common.AppContext
import ru.otus.otuskotlin.social.moodnow.mappers.v1.*
import kotlin.test.Test

@WebFluxTest(ControllerV1::class, MainConfig::class)
internal class ChatterControllerV1Test {
    @Autowired
    private lateinit var webClient: WebTestClient

    @Suppress("unused")
    @MockBean
    private lateinit var processor: AppChatterProcessor

    @Test
    fun createChatter() = testStubChatter(
        "/v1/chatter/create",
        ChatterCreateRequest(),
        AppContext().toTransportCreate().copy(responseType = "create")
    )

    @Test
    fun readChatter() = testStubChatter(
        "/v1/chatter/read",
        ChatterReadRequest(),
        AppContext().toTransportRead().copy(responseType = "read")
    )

    @Test
    fun updateChatter() = testStubChatter(
        "/v1/chatter/update",
        ChatterUpdateRequest(),
        AppContext().toTransportUpdate().copy(responseType = "update")
    )

    @Test
    fun deleteChatter() = testStubChatter(
        "/v1/chatter/delete",
        ChatterDeleteRequest(),
        AppContext().toTransportDelete().copy(responseType = "delete")
    )

    private inline fun <reified Req : Any, reified Res : Any> testStubChatter(
        url: String,
        requestObj: Req,
        responseObj: Res,
    ) {
        webClient
            .post()
            .uri(url)
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(requestObj))
            .exchange()
            .expectStatus().isOk
            .expectBody(Res::class.java)
            .value {
                println("RESPONSE: $it")
                assertThat(it).isEqualTo(responseObj)
            }
    }
}
