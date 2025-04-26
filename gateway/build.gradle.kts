plugins {
    id("java")
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.dependency.management)
}

group = "com.github.diogocerqueiralima"
version = "1.0.0"

extra["springCloudVersion"] = "2024.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.spring.boot.webflux)
    implementation(libs.spring.cloud.gateway)
    implementation(libs.spring.boot.oauth2.client)
    testImplementation(libs.spring.boot.test)
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.test {
    useJUnitPlatform()
}