rootProject.name = "social-moodnow-be"

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

pluginManagement {
    includeBuild("../build-plugin")
    plugins {
        id("build-jvm") apply false
        id("build-kmp") apply false
    }
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

include(":social-moodnow-api-v1-jackson")
include(":social-moodnow-api-v2-kmp")
include(":social-moodnow-api-v1-mappers")
include(":social-moodnow-common")
