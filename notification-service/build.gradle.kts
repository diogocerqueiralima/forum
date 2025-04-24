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
    implementation(libs.spring.boot.mail)
    implementation(libs.spring.boot.oauth2.client)
    implementation(libs.spring.amqp.rabbit)
    testImplementation(libs.spring.boot.test)
}

tasks.test {
    useJUnitPlatform()
}