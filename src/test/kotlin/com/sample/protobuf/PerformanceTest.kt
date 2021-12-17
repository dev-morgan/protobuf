package com.sample.protobuf

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.sample.protobuf.json.JPerson
import com.sample.models.Person
import io.kotlintest.matchers.types.shouldNotBeNull
import io.kotlintest.specs.FunSpec
import mu.KLogging
import kotlin.system.measureTimeMillis

class PerformanceTest : FunSpec({

    test("json vs proto") {
        // json
        val person = JPerson("sam", 10)
        val mapper = ObjectMapper().registerKotlinModule()

        val json = measureTimeMillis {
            val bytes = mapper.writeValueAsBytes(person)
            mapper.readValue(bytes, JPerson::class.java)
        }

        // protobuf
        val sam = Person.newBuilder()
            .setName("sam")
            .setAge(10)
            .build()

        val proto = measureTimeMillis {
            val bytes = sam.toByteArray()
            Person.parseFrom(bytes)
        }

        logger.info("(Json took $json ms)")
        logger.info("(Proto took $proto ms)")

        json.shouldNotBeNull()
        proto.shouldNotBeNull()
    }
}) {
    companion object : KLogging()
}