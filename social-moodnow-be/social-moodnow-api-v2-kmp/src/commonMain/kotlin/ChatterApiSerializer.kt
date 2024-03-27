@file:Suppress("unused")

package ru.otus.otuskotlin.social.moodnow.api.v2

import kotlinx.serialization.json.Json
import ru.otus.otuskotlin.social.moodnow.api.v2.models.IChatterRequest
import ru.otus.otuskotlin.social.moodnow.api.v2.models.IChatterResponse

@Suppress("JSON_FORMAT_REDUNDANT_DEFAULT")
val apiV2Mapper = Json {
//    ignoreUnknownKeys = true
}

@Suppress("unused")
fun apiV2RequestSerialize(obj: IChatterRequest): String = apiV2Mapper.encodeToString(IChatterRequest.serializer(), obj)

@Suppress("UNCHECKED_CAST")
fun <T : IChatterRequest> apiV2RequestDeserialize(json: String) =
    apiV2Mapper.decodeFromString<IChatterRequest>(json) as T

fun apiV2ResponseSerialize(obj: IChatterResponse): String = apiV2Mapper.encodeToString(IChatterResponse.serializer(), obj)

@Suppress("UNCHECKED_CAST")
fun <T : IChatterResponse> apiV2ResponseDeserialize(json: String) =
    apiV2Mapper.decodeFromString<IChatterResponse>(json) as T
