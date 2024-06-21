package ru.otus.otuskotlin.social.moodnow.backend.repo.tests

import ru.otus.otuskotlin.social.moodnow.common.models.*

abstract class BaseInitChatters(private val op: String) : IInitObjects<ModelBaseChatter> {

    fun createInitTestModel(
        suf: String,
    ) = ModelBaseChatter(
        id = ModelChatterId("chatter-repo-$op-$suf"),
        message = ModelChatterContentMessage("$suf stub"),
        ownerId = ModelUserId.NONE,
        authorNickName = "",
    )
}
