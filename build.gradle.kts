plugins {
    kotlin("jvm") version "1.9.10"
    application
}

group = "ru.dumdumbich"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    implementation("org.apache.commons:commons-text:1.10.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.8.10")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("ru.dumdumbich.deploy.AppKt")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "ru.dumdumbich.deploy.AppKt"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}
