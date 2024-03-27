plugins {
    id("build-jvm")
}

group = rootProject.group
version = rootProject.version

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":social-moodnow-api-v1-jackson"))
    implementation(project(":social-moodnow-common"))

    testImplementation(kotlin("test-junit"))
}
