package com.sample.protobuf

import com.sample.models.Television
import java.nio.file.Files
import java.nio.file.Paths

fun main() {
//    val television = Television.newBuilder()
//        .setBrand("sony")
//        .setYear(2015)
//        .build()
//
//    val pathV1 = Paths.get("tv-v1")
//    Files.write(pathV1, television.toByteArray())

//    val television = Television.newBuilder()
//        .setBrand("sony")
//        .setModel(2016)
//        .setType(Type.OLED)
//        .build()

//    val pathV2 = Paths.get("tv-1")
//    Files.write(pathV2, television.toByteArray())

    val pathV1 = Paths.get("tv-v1")
    val bytes = Files.readAllBytes(pathV1)

    println(
        Television.parseFrom(bytes).price
    )
}
