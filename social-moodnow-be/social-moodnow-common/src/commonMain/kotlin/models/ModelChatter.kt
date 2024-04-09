package ru.otus.otuskotlin.social.moodnow.common.models

data class ModelChatter(
    var id: ModelChatterId = ModelChatterId.NONE,

    var message: ModelChatterContentMessage = ModelChatterContentMessage.NONE

) {
    companion object {
        private val NONE = ModelChatter()
    }
}
