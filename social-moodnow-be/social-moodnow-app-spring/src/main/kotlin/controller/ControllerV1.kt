package ru.otus.otuskotlin.social.moodnow.app.spring.controller

import org.springframework.web.bind.annotation.*
import ru.otus.otuskotlin.social.moodnow.api.v1.models.*
import ru.otus.otuskotlin.social.moodnow.app.common.controllerHelper
import ru.otus.otuskotlin.social.moodnow.app.spring.config.AppSettings
import ru.otus.otuskotlin.social.moodnow.mappers.v1.fromTransport
import ru.otus.otuskotlin.social.moodnow.mappers.v1.toTransport
import kotlin.reflect.KClass

@Suppress("unused")
@RestController
@RequestMapping("v1/chatter")
class ControllerV1(
    private val appSettings: AppSettings
) {

    @PostMapping("create")
    suspend fun create(@RequestBody request: ChatterCreateRequest): ChatterCreateResponse =
        process(
            appSettings, request = request, this::class, "create"
        )

    @PostMapping("read")
    suspend fun read(@RequestBody request: ChatterReadRequest): ChatterReadResponse =
        process(
            appSettings, request = request, this::class, "read"
        )

    @PostMapping("update")
    suspend fun update(@RequestBody request: ChatterUpdateRequest): ChatterUpdateResponse =
        process(
            appSettings, request = request, this::class, "update"
        )

    @PostMapping("delete")
    suspend fun delete(@RequestBody request: ChatterDeleteRequest): ChatterDeleteResponse =
        process(
            appSettings, request = request, this::class, "delete"
        )


    companion object {
        suspend inline fun <reified Q : IChatterRequest, reified R : IChatterResponse> process(
            appSettings: AppSettings,
            request: Q,
            clazz: KClass<*>,
            logId: String,
        ): R = appSettings.controllerHelper(
            { fromTransport(request) },
            { toTransport() as R },
            clazz,
            logId,
        )
    }
}