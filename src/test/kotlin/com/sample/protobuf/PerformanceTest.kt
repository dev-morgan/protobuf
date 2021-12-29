package com.sample.protobuf

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.google.protobuf.Int32Value
import com.sample.models.Person
import com.sample.protobuf.json.JPerson
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
            logger.info("json bytes size : ${bytes.size}")
            mapper.readValue(bytes, JPerson::class.java)
        }

        // protobuf
        val sam = Person.newBuilder().apply {
            name = "sam"
            age = Int32Value.newBuilder().setValue(10).build()
        }.build()

        val proto = measureTimeMillis {
            val bytes = sam.toByteArray()
            logger.info("proto bytes size : ${bytes.size}")
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
