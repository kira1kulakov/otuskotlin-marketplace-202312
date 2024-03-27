package ru.otus.otuskotlin.social.moodnow.api.v1

import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.json.JsonMapper
import ru.otus.otuskotlin.social.moodnow.api.v1.models.IChatterRequest
import ru.otus.otuskotlin.social.moodnow.api.v1.models.IChatterResponse


val apiV1Mapper = JsonMapper.builder().run {
    enable(MapperFeature.USE_BASE_TYPE_AS_DEFAULT_IMPL)
    build()
}

@Suppress("unused")
fun apiV1RequestSerialize(request: IChatterRequest): String = apiV1Mapper.writeValueAsString(request)

@Suppress("UNCHECKED_CAST", "unused")
fun <T : IChatterRequest> apiV1RequestDeserialize(json: String): T =
    apiV1Mapper.readValue(json, IChatterRequest::class.java) as T

@Suppress("unused")
fun apiV1ResponseSerialize(response: IChatterResponse): String = apiV1Mapper.writeValueAsString(response)

@Suppress("UNCHECKED_CAST", "unused")
fun <T : IChatterResponse> apiV1ResponseDeserialize(json: String): T =
    apiV1Mapper.readValue(json, IChatterResponse::class.java) as T
