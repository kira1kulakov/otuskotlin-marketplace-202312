package ru.otus.otuskotlin.social.moodnow.api.v1

import ru.otus.otuskotlin.social.moodnow.api.v1.models.*
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class ChatterRequestV1SerializationTest {

    private val request = ChatterCreateRequest(
        chatter = ChatterCreateObject(
            message = "Test text for message",
        )
    )

    @Test
    fun serialize() {
        val json = apiV1Mapper.writeValueAsString(request)

        assertContains(json, Regex("\"message\":\\s*\"Test text for message\""))
        assertContains(json, Regex("\"requestType\":\\s*\"create\""))
    }

    @Test
    fun deserialize() {
        val json = apiV1Mapper.writeValueAsString(request)
        val obj = apiV1Mapper.readValue(json, IChatterRequest::class.java) as ChatterCreateRequest

        assertEquals(request, obj)
    }

    @Test
    fun deserializeNaked() {
        val jsonString = """
            {"chatter": null}
        """.trimIndent()
        val obj = apiV1Mapper.readValue(jsonString, ChatterCreateRequest::class.java)

        assertEquals(null, obj.chatter)
    }
}
