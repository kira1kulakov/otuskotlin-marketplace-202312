package ru.otus.otuskotlin.social.moodnow.mappers.exceptions

class UnknownRequestClass(clazz: Class<*>) : RuntimeException("Class $clazz cannot be mapped to AppContext")