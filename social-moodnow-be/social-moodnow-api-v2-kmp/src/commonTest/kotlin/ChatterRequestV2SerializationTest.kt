package ru.otus.otuskotlin.social.moodnow.api.v2

import kotlinx.serialization.encodeToString
import ru.otus.otuskotlin.social.moodnow.api.v2.models.ChatterCreateObject
import ru.otus.otuskotlin.social.moodnow.api.v2.models.ChatterCreateRequest
import ru.otus.otuskotlin.social.moodnow.api.v2.models.IChatterRequest
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class ChatterRequestV2SerializationTest {

    private val request: IChatterRequest = ChatterCreateRequest(
        chatter = ChatterCreateObject(
            message = "Test text for message",
        )
    )

    @Test
    fun serialize() {
        val json = apiV2Mapper.encodeToString(IChatterRequest.serializer(), request)

        println(json)

        assertContains(json, Regex("\"message\":\\s*\"Test text for message\""))
        assertContains(json, Regex("\"requestType\":\\s*\"create\""))
    }

    @Test
    fun deserialize() {
        val json = apiV2Mapper.encodeToString(request)
        val obj = apiV2Mapper.decodeFromString<IChatterRequest>(json) as ChatterCreateRequest

        assertEquals(request, obj)
    }

    @Test
    fun deserializeNaked() {
        val jsonString = """
            {"chatter": null}
        """.trimIndent()
        val obj = apiV2Mapper.decodeFromString<ChatterCreateRequest>(jsonString)

        assertEquals(null, obj.chatter)
    }
}