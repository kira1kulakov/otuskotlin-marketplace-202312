package ru.otus.otuskotlin.social.moodnow.common

import ru.otus.otuskotlin.social.moodnow.logging.common.MpLoggerProvider
import ru.otus.otuskotlin.social.moodnow.common.repo.IRepoChatter

data class AppCorSettings(
    val loggerProvider: MpLoggerProvider = MpLoggerProvider(),
    val repoStub: IRepoChatter = IRepoChatter.NONE,
    val repoTest: IRepoChatter = IRepoChatter.NONE,
    val repoProd: IRepoChatter = IRepoChatter.NONE,
) {

    companion object {
        val NONE = AppCorSettings()
    }
}