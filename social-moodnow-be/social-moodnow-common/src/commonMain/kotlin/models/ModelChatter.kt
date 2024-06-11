package ru.otus.otuskotlin.social.moodnow.common.models

data class ModelChatter(
    var id: ModelChatterId = ModelChatterId.NONE,

    var message: ModelChatterContentMessage = ModelChatterContentMessage.NONE

) {
    fun deepCopy(): ModelChatter = copy()

    fun baseCopy(): ModelBaseChatter = ModelBaseChatter(
        id = id?.let { it } ?: ModelChatterId.NONE,
        message = message?.let { it } ?: ModelChatterContentMessage.NONE,
        ownerId = ModelUserId.NONE,
        authorNickName = "",
    )

    companion object {
        private val NONE = ModelChatter()
    }
}
