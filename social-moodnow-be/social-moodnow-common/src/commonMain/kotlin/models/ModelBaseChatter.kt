package ru.otus.otuskotlin.social.moodnow.common.models

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

data class ModelBaseChatter(
    val id: ModelChatterId = ModelChatterId.NONE,
    val ownerId: ModelUserId = ModelUserId.NONE,
    val authorNickName: String = "",
    val message: ModelChatterContentMessage = ModelChatterContentMessage.NONE,
    val createdAt: Instant = Clock.System.now()
)
