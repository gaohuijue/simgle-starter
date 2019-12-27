import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    kotlin("jvm") version "1.3.61"
    `maven-publish`
}

group = "com.simgle"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    maven("http://maven.aliyun.com/nexus/content/groups/public/")
    maven("http://maven.aliyun.com/nexus/content/repositories/jcenter")
    mavenLocal()
}

dependencies {
    api("com.simgle:simgle-core:+")
    api("com.simgle:simgle-persistence:+")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.2.2.RELEASE") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
