package ru.otus.otuskotlin.social.moodnow.repo.inmemory

import ru.otus.otuskotlin.social.moodnow.common.models.*

data class ChatterEntity(
    val id: String? = null,
    val message: String? = null,
    val ownerId: String? = null,
    val authorNickName: String? = null,
) {
    constructor(model: ModelBaseChatter) : this(
        id = model.id.asString().takeIf { it.isNotBlank() },
        message = model.message.asString().takeIf { it.isNotBlank() },
        ownerId = model.ownerId.asString().takeIf { it.isNotBlank() },
        authorNickName = "",
    )

    fun toInternal() = ModelBaseChatter(
        id = id?.let { ModelChatterId(it) } ?: ModelChatterId.NONE,
        message = message?.let { ModelChatterContentMessage(it) } ?: ModelChatterContentMessage.NONE,
        ownerId = ownerId?.let { ModelUserId(it) } ?: ModelUserId.NONE,
        authorNickName = authorNickName ?: "",
    )
}
