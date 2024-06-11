package ru.otus.otuskotlin.social.moodnow.repo.common

import ru.otus.otuskotlin.social.moodnow.common.models.ModelBaseChatter

/**
 * Делегат для всех репозиториев, позволяющий инициализировать базу данных предзагруженными данными
 */
class ChatterRepoInitialized(
    private val repo: IRepoChatterInitializable,
    private val initObjects: Collection<ModelBaseChatter> = emptyList(),
) : IRepoChatterInitializable by repo {
    @Suppress("unused")
    val initializedObjects: List<ModelBaseChatter> = save(initObjects).toList()
}
