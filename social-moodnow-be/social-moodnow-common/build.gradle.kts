plugins {
    id("build-kmp")
}

group = rootProject.group
version = rootProject.version

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))

                api(libs.kotlinx.datetime)
                api("ru.otus.otuskotlin.social.moodnow.libs:social-moodnow-lib-logging-common")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
    }
}
