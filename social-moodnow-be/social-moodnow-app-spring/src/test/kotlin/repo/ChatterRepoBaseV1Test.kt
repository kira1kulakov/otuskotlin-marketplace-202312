package ru.otus.otuskotlin.social.moodnow.app.spring.repo

import org.assertj.core.api.Assertions.assertThat
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters
import ru.otus.otuskotlin.social.moodnow.api.v1.models.*
import ru.otus.otuskotlin.social.moodnow.common.AppContext
import ru.otus.otuskotlin.social.moodnow.common.enums.State
import ru.otus.otuskotlin.social.moodnow.common.models.ModelBaseChatter
import ru.otus.otuskotlin.social.moodnow.common.models.ModelChatterContentMessage
import ru.otus.otuskotlin.social.moodnow.common.models.ModelChatterId
import ru.otus.otuskotlin.social.moodnow.common.models.ModelUserId
import ru.otus.otuskotlin.social.moodnow.mappers.v1.toTransportCreate
import ru.otus.otuskotlin.social.moodnow.mappers.v1.toTransportDelete
import ru.otus.otuskotlin.social.moodnow.mappers.v1.toTransportRead
import ru.otus.otuskotlin.social.moodnow.mappers.v1.toTransportUpdate
import ru.otus.otuskotlin.social.moodnow.stubs.AppChatterStub
import kotlin.test.Test

internal abstract class ChatterRepoBaseV1Test {
    protected abstract var webClient: WebTestClient
    protected val uuidNew = "00000000-0000-0000-0000-000000000000"

    @Test
    open fun createChatter() = testRepoChatter(
        "create",
        ChatterCreateRequest(
            chatter = AppChatterStub.getAnyResponse().toTransportCreate(),
        ),
        prepareCtx(
            AppChatterStub.prepareResult {
                id = ModelChatterId(uuidNew)
                message = ModelChatterContentMessage("Default text of message")
                ownerId = ModelUserId.NONE
                authorNickName = ""
            }
        )
            .toTransportCreate()
            .copy(responseType = "create")
    )

    @Test
    open fun readChatter() = testRepoChatter(
        "read",
        ChatterReadRequest(
            chatter = AppChatterStub.getAnyResponse().toTransportRead(),
        ),
        prepareCtx(
            AppChatterStub.getAnyResponse()
        )
            .toTransportRead()
            .copy(responseType = "read")
    )

    @Test
    open fun updateChatter() = testRepoChatter(
        "update",
        ChatterUpdateRequest(
            chatter = AppChatterStub.prepareResult { message = ModelChatterContentMessage("changeit") }
                .toTransportUpdate(),
        ),
        prepareCtx(
            AppChatterStub.prepareResult { message = ModelChatterContentMessage("changeit") }
        )
            .toTransportUpdate()
            .copy(responseType = "update")
    )

    @Test
    open fun deleteChatter() = testRepoChatter(
        "delete",
        ChatterDeleteRequest(
            chatter = AppChatterStub.getAnyResponse().toTransportDelete(),
        ),
        prepareCtx(
            AppChatterStub.getAnyResponse()
        )
            .toTransportDelete()
            .copy(responseType = "delete")
    )


    private fun prepareCtx(chatter: ModelBaseChatter) = AppContext(
        state = State.PROCESSING,
        chatterResponse = chatter
    )

    private inline fun <reified Req : Any, reified Res : IChatterResponse> testRepoChatter(
        url: String,
        requestObj: Req,
        expectObj: Res,
    ) {
        webClient
            .post()
            .uri("/v1/chatter/$url")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(requestObj))
            .exchange()
            .expectStatus().isOk
            .expectBody(Res::class.java)
            .value {
                println("RESPONSE: $it")
                val sortedResp: IChatterResponse = it
                assertThat(sortedResp).isEqualTo(expectObj)
            }
    }
}
