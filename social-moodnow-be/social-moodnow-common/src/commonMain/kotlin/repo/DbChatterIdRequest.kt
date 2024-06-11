package ru.otus.otuskotlin.social.moodnow.common.repo

import ru.otus.otuskotlin.social.moodnow.common.models.ModelBaseChatter
import ru.otus.otuskotlin.social.moodnow.common.models.ModelChatterId

data class DbChatterIdRequest(
    val id: ModelChatterId,
) {
    constructor(chatter: ModelBaseChatter) : this(chatter.id)
}
