package ru.otus.otuskotlin.social.moodnow.app.common

import kotlinx.datetime.Clock
import ru.otus.otuskotlin.social.moodnow.common.AppContext
import ru.otus.otuskotlin.social.moodnow.common.helpers.asMkplError
import ru.otus.otuskotlin.social.moodnow.common.enums.State
import kotlin.reflect.KClass

suspend inline fun <T> IAppSettings.controllerHelper(
    crossinline getRequest: suspend AppContext.() -> Unit,
    crossinline toResponse: suspend AppContext.() -> T,
    clazz: KClass<*>,
    logId: String
): T {
    val logger = corSettings.loggerProvider.logger(clazz)
    val ctx = AppContext(
        timeStart = Clock.System.now()
    )

    return try {
        ctx.getRequest()
        logger.info(
            msg = "Request $logId started for ${clazz.simpleName}",
            marker = "BIZ"
        )
        processor.exec(ctx)
        logger.info(
            msg = "Request $logId processed for ${clazz.simpleName}",
            marker = "BIZ"
        )
        ctx.toResponse()
    } catch (ex: Throwable) {
        logger.error(
            msg = "Request $logId failed for ${clazz.simpleName}",
            marker = "BIZ"
        )
        ctx.state = State.FAILING
        ctx.errors.add(ex.asMkplError())
        processor.exec(ctx)
        ctx.toResponse()
    }
}