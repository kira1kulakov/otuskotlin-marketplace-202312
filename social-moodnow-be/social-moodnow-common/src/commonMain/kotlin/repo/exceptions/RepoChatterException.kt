package ru.otus.otuskotlin.social.moodnow.common.repo.exceptions

import ru.otus.otuskotlin.social.moodnow.common.models.ModelChatterId

open class RepoChatterException(
    @Suppress("unused")
    val chatterId: ModelChatterId,
    msg: String,
) : RepoException(msg)
