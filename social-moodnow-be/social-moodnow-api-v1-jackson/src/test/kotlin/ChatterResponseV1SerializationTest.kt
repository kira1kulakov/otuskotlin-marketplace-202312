package ru.otus.otuskotlin.social.moodnow.api.v1

import ru.otus.otuskotlin.social.moodnow.api.v1.models.*
import java.util.UUID
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class ChatterResponseV1SerializationTest {

    private val uuid:String = UUID.randomUUID().toString()
    private val ownerUuid:String = UUID.randomUUID().toString()
    private val authorNickName = "author1"
    private val message = "test message"
    private val createdAt = "2024-04-08T12:00:10"

    private val response = ChatterCreateResponse(
        chatter = BaseChatter(
            id = uuid,
            ownerId = ownerUuid,
            authorNickName = authorNickName,
            message = message,
            createdAt = createdAt
        )
    )

    @Test
    fun serialize() {
        val json = apiV1Mapper.writeValueAsString(response)

        assertContains(json, Regex("\"id\":\\s*\"$uuid\""))
        assertContains(json, Regex("\"ownerId\":\\s*\"$ownerUuid\""))
        assertContains(json, Regex("\"authorNickName\":\\s*\"$authorNickName\""))
        assertContains(json, Regex("\"message\":\\s*\"$message\""))
        assertContains(json, Regex("\"createdAt\":\\s*\"$createdAt\""))
    }

    @Test
    fun deserialize() {
        val json = apiV1Mapper.writeValueAsString(response)
        val obj = apiV1Mapper.readValue(json, IChatterResponse::class.java) as ChatterCreateResponse

        assertEquals(response, obj)
    }
}