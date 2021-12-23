package com.sample.protobuf

import com.sample.models.Television
import com.sample.models.Type
import io.kotlintest.specs.FunSpec
import mu.KLogging
import java.nio.file.Files
import java.nio.file.Paths

class VersionCompatibilityTest : FunSpec({
    test("read & write with version") {
//    val television = Television.newBuilder()
//        .setBrand("sony")
//        .setYear(2015)
//        .build()
//
//    val pathV1 = Paths.get("tv-v1")
//    Files.write(pathV1, television.toByteArray())

        val television = Television.newBuilder()
            .setBrand("sony")
            .setPrice(100_000)
            .setType(Type.OLED)
            .build()

        val pathV2 = Paths.get("tv-v2")
        Files.write(pathV2, television.toByteArray())

        val pathV1 = Paths.get("tv-v1")
        val bytes = Files.readAllBytes(pathV1)

        logger.info { Television.parseFrom(bytes).price }
    }
}) {
    companion object : KLogging()
}
