package ru.otus.otuskotlin.social.moodnow.common.models

import kotlin.jvm.JvmInline

@JvmInline
value class ModelUserId(private val id: String) {

    fun asString() = id

    companion object {
        val NONE = ModelUserId("")
    }
}