import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.4"
    id("com.ewerk.gradle.plugins.querydsl") version "1.0.10"
    war

    val kotlinVersion = "2.1.0"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
    kotlin("kapt") version  kotlinVersion

    id("de.undercouch.download") version "5.3.0"
    idea
}

group = "com.doinglab"
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_21

repositories {
    mavenCentral()
}

extra.apply {
    set("snippetsDir", file("build/generated-snippets"))
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:2023.0.1")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-log4j2")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.springframework.boot:spring-boot-starter-mail")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    runtimeOnly("org.springframework.boot:spring-boot-starter-tomcat")
    implementation("org.springframework.boot:spring-boot-starter-activemq")
    implementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

    val querydslVersion = "5.1.0"
    implementation("com.querydsl:querydsl-jpa:$querydslVersion:jakarta")
    annotationProcessor("com.querydsl:querydsl-apt:$querydslVersion:jakarta")
    kapt("com.querydsl:querydsl-apt:$querydslVersion:jakarta")

    runtimeOnly("com.mysql:mysql-connector-j:8.3.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-hibernate5")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("org.apache.httpcomponents:httpclient")

    implementation("com.slack.api:slack-api-client:1.30.0")

    // jwt
    implementation("io.jsonwebtoken:jjwt:0.9.1")

    // jwt 토큰 발급 시 해당 모듈 없으면 exception 발생
    val openApiVersion = "2.6.0"
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$openApiVersion")

    // aws
    implementation("com.amazonaws:aws-java-sdk-s3:1.12.290") // amazon auth, client, service, ...

    implementation("com.ibm.icu:icu4j:71.1")

    // local cache - caffeine
    implementation("com.github.ben-manes.caffeine:caffeine")

    implementation("org.messaginghub:pooled-jms")
    implementation("org.apache.commons:commons-text:1.10.0")

    implementation("io.sentry:sentry-spring-boot-starter-jakarta:7.16.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        testImplementation("com.ninja-squad:springmockk:3.1.2")
        exclude(group = "junit")
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

configurations {
    forEach {
        it.exclude("org.springframework.boot", "spring-boot-starter-logging")
    }
}


tasks {
    withType<KotlinJvmCompile>().configureEach {
        compilerOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict -Xlint:preview --release 21 --enable-preview")
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }

    withType<Test> {
        useJUnitPlatform()
        outputs.dir(ext["snippetsDir"]!!)
    }

    withType<War> {
        enabled = true

        webInf {
            from(".ebextensions") {
                into("/.ebextensions")
            }
            from(".platform") {
                into("/.platform")
            }
        }
    }

    withType<JavaExec> {
        jvmArgs = listOf("--enable-preview",)
    }
}

kotlin.sourceSets.main {
    kotlin.srcDir("build/generated/source/kapt/main")
}
