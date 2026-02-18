plugins {
	kotlin("jvm") version "2.2.0+"
	kotlin("plugin.spring") version "2.2.0+"
	id("org.springframework.boot") version "3.5.4" // "4.0.0-M1+" //"3.5.4"
	id("io.spring.dependency-management") version "1.1.7+"
	id("org.graalvm.buildtools.native") version "0.10.6" //"0.10.6+"
    //id("org.springframework.experimental.aot") version "0.12.1"  Spring Boot 3.x has AOT support built-in, so you don’t need this plugin anymore.
    java
}

group = "com.graalVM"
version = "0.0.1-SNAPSHOT"
description = "Demo project for Spring Boot"

val JAVA_VERSION = 25
java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(JAVA_VERSION)
	}
}

tasks.named<org.springframework.boot.gradle.tasks.bundling.BootBuildImage>("bootBuildImage") {
    imageName.set("labbdocker/graalvmlabb")
    builder.set("paketobuildpacks/builder-jammy-buildpackless-tiny")
    environment.put("BP_JVM_VERSION", JAVA_VERSION.toString())
    buildpacks.set(
        listOf(//            "paketobuildpacks/oracle",
            "paketobuildpacks/java-native-image"
        )
    )
}

repositories {
	mavenCentral()
}

dependencies {
//Log4j2 is not fully supported in GraalVM native images
    implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.8+")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

