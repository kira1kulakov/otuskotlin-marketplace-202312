package ru.otus.otuskotlin.social.moodnow.repo.common

import ru.otus.otuskotlin.social.moodnow.common.models.ModelBaseChatter
import ru.otus.otuskotlin.social.moodnow.common.repo.IRepoChatter

interface IRepoChatterInitializable : IRepoChatter {
    fun save(chatters: Collection<ModelBaseChatter>): Collection<ModelBaseChatter>
}
