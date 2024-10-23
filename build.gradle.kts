plugins {
    id("java")
}

group = "co.edu.uniquindio"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // No necesitas crear la configuración compileOnly; puedes usarla directamente.
    compileOnly("org.projectlombok:lombok:1.18.26") // Especifica la versión
    annotationProcessor("org.projectlombok:lombok:1.18.26") // Especifica la versión
}

tasks.test {
    useJUnitPlatform()
}
