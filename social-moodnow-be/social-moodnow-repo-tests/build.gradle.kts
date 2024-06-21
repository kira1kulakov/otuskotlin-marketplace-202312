plugins {
    id("build-kmp")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(kotlin("test-common"))
                api(kotlin("test-annotations-common"))

                api(libs.coroutines.core)
                api(libs.coroutines.test)
                implementation(project(":social-moodnow-common"))
                implementation(project(":social-moodnow-repo-common"))
            }
        }
        commonTest {
            dependencies {
                implementation(project(":social-moodnow-stubs"))
            }
        }
        jvmMain {
            dependencies {
                api(kotlin("test-junit"))
            }
        }
    }
}
