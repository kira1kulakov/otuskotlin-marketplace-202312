pluginManagement {
    plugins {
        val kotlinVersion: String by settings
        kotlin("jvm") version kotlinVersion
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "otuskotlin-marketplace-202312"

include("m1l1-first")
include("m1l2-basis")
include("m1l3-functions-collections")
include("m1l4-oop")
include("m1l5-dsl")
include("m2l1-coroutines")
include("m2l2-flows")
include("m2l3-kmp")
include("m2l4-1-interop")
include("m2l4-2-rust")
include("m2l4-3-jni")
include("m2l5-gradle")
