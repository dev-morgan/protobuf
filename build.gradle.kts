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
            artifact = "io.grpc:protoc-gen-grpc-java:1.42.1"
        }
    }
    generateProtoTasks {
        ofSourceSet("main").forEach { task ->
            task.plugins {
                id("grpc")
            }
            task.builtins {
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

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("io.grpc:grpc-protobuf:1.42.1")
    implementation("com.google.api.grpc:proto-google-common-protos:2.7.0")

    implementation("org.apache.tomcat:tomcat-annotations-api:10.0.14")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.0")

    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.4.2")

}

tasks {
    withType<Copy> {
        filesMatching("**/*.proto") {
            duplicatesStrategy = DuplicatesStrategy.INCLUDE
        }
    }

    withType<Test> {
        useJUnitPlatform()
    }
}
