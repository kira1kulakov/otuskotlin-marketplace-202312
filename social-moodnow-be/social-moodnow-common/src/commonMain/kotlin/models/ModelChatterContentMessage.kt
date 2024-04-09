package ru.otus.otuskotlin.social.moodnow.common.models

import kotlin.jvm.JvmInline

@JvmInline
value class ModelChatterContentMessage(private val value: String) {

    fun asString() = value

    companion object {
        val NONE = ModelChatterContentMessage("")
    }
}