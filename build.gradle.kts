import com.google.protobuf.gradle.*

buildscript {
    repositories {
        maven {
            setUrl("https://plugins.gradle.org/m2/")
            mavenCentral()
        }
    }
    dependencies {
        classpath("com.google.protobuf:protobuf-gradle-plugin:0.8.18")
    }
}

plugins {
    idea
    kotlin("jvm") version "1.6.10"
    id("com.google.protobuf") version "0.8.18"
}

group = "com.sample"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.19.1"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.43.1" // The protoc plugin for gRPC Java
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:1.2.0:jdk7@jar"
        }
    }
    generateProtoTasks {
        ofSourceSet("main").forEach { task ->
            task.plugins {
                id("grpc")
                id("grpckt")
            }
            task.builtins {
                id("kotlin")
                create("js")
            }
        }
    }
}

idea {
    module {
        generatedSourceDirs.add(file("${protobuf.protobuf.generatedFilesBaseDir}/main/grpc"))
    }
}

tasks {
    test {
        useJUnitPlatform()
    }
}

val kotlinLoggingVersion = "2.1.21"
dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("io.github.microutils:kotlin-logging:$kotlinLoggingVersion")
    implementation("ch.qos.logback:logback-classic:1.2.9")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("io.grpc:grpc-protobuf:1.43.1")
    implementation("io.grpc:grpc-kotlin-stub:1.2.0")

    implementation("com.google.protobuf:protobuf-kotlin:3.19.1")

    implementation("org.apache.tomcat:tomcat-annotations-api:10.0.14")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.1")

    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.4.2")
}
