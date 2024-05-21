package ru.otus.otuskotlin.social.moodnow.stubs

import ru.otus.otuskotlin.social.moodnow.common.models.*
import ru.otus.otuskotlin.social.moodnow.stubs.AppChatterStubAny.CHATTER_ANY_REQUEST_COPY
import ru.otus.otuskotlin.social.moodnow.stubs.AppChatterStubAny.CHATTER_ANY_RESPONSE_COPY

object AppChatterStub {

    fun getAnyRequest(): ModelChatter = CHATTER_ANY_REQUEST_COPY.copy()

    fun getAnyResponse(): ModelBaseChatter = CHATTER_ANY_RESPONSE_COPY.copy()
}