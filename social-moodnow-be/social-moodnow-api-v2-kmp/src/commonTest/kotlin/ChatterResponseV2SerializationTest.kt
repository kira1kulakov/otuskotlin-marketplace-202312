package ru.otus.otuskotlin.social.moodnow.api.v2

import kotlinx.serialization.encodeToString
import ru.otus.otuskotlin.social.moodnow.api.v2.models.BaseChatter
import ru.otus.otuskotlin.social.moodnow.api.v2.models.ChatterCreateResponse
import ru.otus.otuskotlin.social.moodnow.api.v2.models.IChatterResponse
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class ChatterResponseV2SerializationTest {

    private val uuid = "bd3de220-4bc5-45d6-8248-57ac9cdc2afa"
    private val ownerUuid = "213613b3-e665-41a1-b6f6-52184f04abfe"
    private val authorNickName = "author1"
    private val message = "test message"
    private val createdAt = "2024-04-08T12:00:10"

    private val response: IChatterResponse = ChatterCreateResponse(
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
        val json = apiV2Mapper.encodeToString(IChatterResponse.serializer(), response)

        println(json)

        assertContains(json, Regex("\"id\":\\s*\"$uuid\""))
        assertContains(json, Regex("\"ownerId\":\\s*\"$ownerUuid\""))
        assertContains(json, Regex("\"authorNickName\":\\s*\"$authorNickName\""))
        assertContains(json, Regex("\"message\":\\s*\"$message\""))
        assertContains(json, Regex("\"createdAt\":\\s*\"$createdAt\""))
        assertContains(json, Regex("\"responseType\":\\s*\"create\""))
    }


    @Test
    fun deserialize() {
        val json = apiV2Mapper.encodeToString(response)
        val obj = apiV2Mapper.decodeFromString<IChatterResponse>(json) as ChatterCreateResponse

        assertEquals(response, obj)
    }
}