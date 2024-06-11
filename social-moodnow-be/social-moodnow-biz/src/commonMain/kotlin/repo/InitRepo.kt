package ru.otus.otuskotlin.social.moodnow.biz.repo

import ru.otus.otuskotlin.social.moodnow.biz.exceptions.DbNotConfiguredException
import ru.otus.otuskotlin.social.moodnow.common.AppContext
import ru.otus.otuskotlin.social.moodnow.common.helpers.errorSystem
import ru.otus.otuskotlin.social.moodnow.common.helpers.fail
import ru.otus.otuskotlin.social.moodnow.common.enums.AppWorkMode
import ru.otus.otuskotlin.social.moodnow.common.repo.IRepoChatter
import ru.otus.otuskotlin.social.moodnow.cor.ICorChainDsl
import ru.otus.otuskotlin.social.moodnow.cor.worker

fun ICorChainDsl<AppContext>.initRepo(title: String) = worker {
    this.title = title
    description = """
        Вычисление основного рабочего репозитория в зависимости от запрошенного режима работы        
    """.trimIndent()
    handle {
        chatterRepo = when {
            workMode == AppWorkMode.TEST -> corSettings.repoTest
            workMode == AppWorkMode.STUB -> corSettings.repoStub
            else -> corSettings.repoProd
        }
        if (workMode != AppWorkMode.STUB && chatterRepo == IRepoChatter.NONE) fail(
            errorSystem(
                violationCode = "dbNotConfigured",
                e = DbNotConfiguredException(workMode)
            )
        )
    }
}
