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
    implementation(libs.spring.boot.data.jpa)
    implementation(libs.spring.amqp.rabbit)
    implementation(libs.spring.boot.oauth2.resource.server)
    implementation(libs.spring.boot.validation)
    implementation(libs.springdoc)
    implementation(libs.postgresql)
    testImplementation(libs.spring.boot.test)
}

tasks.test {
    useJUnitPlatform()
}