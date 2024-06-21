package ru.otus.otuskotlin.social.moodnow.common.models

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

data class ModelBaseChatter(
    var id: ModelChatterId = ModelChatterId.NONE,
    var ownerId: ModelUserId = ModelUserId.NONE,
    var authorNickName: String = "",
    var message: ModelChatterContentMessage = ModelChatterContentMessage.NONE,
    var createdAt: Instant? = null
) {
    fun deepCopy(): ModelBaseChatter = copy()
}
