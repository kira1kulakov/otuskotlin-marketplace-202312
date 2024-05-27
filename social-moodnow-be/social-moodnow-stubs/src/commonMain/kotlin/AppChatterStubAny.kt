package ru.otus.otuskotlin.social.moodnow.stubs

import ru.otus.otuskotlin.social.moodnow.common.models.*

object AppChatterStubAny {

    private val ANY_ID = "00000000-0000-0000-0000-000000000000"
    private val ANY_MESSAGE = "Default text of message"

    val CHATTER_ANY: ModelChatter
        get() = ModelChatter(
            ModelChatterId(ANY_ID),
            ModelChatterContentMessage(ANY_MESSAGE)
        )

    val CHATTER_BASE_ANY: ModelBaseChatter
        get() = ModelBaseChatter(
            id = ModelChatterId(ANY_ID),
            ownerId = ModelUserId("1"),
            authorNickName = "Tony Stark",
            message = ModelChatterContentMessage(ANY_MESSAGE)
        )

    val CHATTER_ANY_REQUEST_COPY = CHATTER_ANY.copy()

    val CHATTER_ANY_RESPONSE_COPY = CHATTER_BASE_ANY.copy()
}