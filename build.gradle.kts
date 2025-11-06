plugins {
    kotlin("jvm") version "2.2.20"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    // Dependencias de JSON (Jackson)
    // 1. Core de Jackson para manejar la lectura (readValue)
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")

    // 2. Módulo de Kotlin para soportar Data Classes (PuntoDeDato)
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.2")

    // Dependencia XChart
    implementation("org.knowm.xchart:xchart:3.8.5")


}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}