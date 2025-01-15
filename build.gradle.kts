plugins {
    kotlin("jvm") version "2.0.21"
}

group = "ckh.study"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("io.kotest:kotest-runner-junit5:5.9.1") // Kotest JUnit5 러너
    testImplementation("io.kotest:kotest-assertions-core:5.9.1") // Kotest Assertions
    testImplementation("io.kotest:kotest-property:5.9.1") // Kotest Property
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}