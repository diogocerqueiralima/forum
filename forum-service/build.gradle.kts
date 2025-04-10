plugins {
    id("java")
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.dependency.management)
}

group = "com.github.diogocerqueiralima"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.spring.boot)
    implementation(libs.spring.boot.web)
    implementation(libs.spring.data.jpa)
    implementation(libs.postgresql)
    testImplementation(libs.spring.boot.test)
}

tasks.test {
    useJUnitPlatform()
}