package com.sample.morgan.protobuf

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.sample.morgan.json.JPerson
import com.sample.morgan.models.Person
import io.kotlintest.specs.FunSpec

class PerformanceTest : FunSpec({
    test("json vs proto") {
        // json
        val person = JPerson("sam", 10)
        val mapper = ObjectMapper().registerKotlinModule()

        val json = Runnable {
            val bytes = mapper.writeValueAsBytes(person)
            mapper.readValue(bytes, JPerson::class.java)
        }

        // protobuf
        val sam = Person.newBuilder()
            .setName("sam")
            .setAge(10)
            .build()

        val proto = Runnable {
            val bytes = sam.toByteArray()
            Person.parseFrom(bytes)
        }

        for (i in 1..5) {
            runPerformanceTest(json, "json")
            runPerformanceTest(proto, "protobuf")
        }
    }
})

fun runPerformanceTest(runnable: Runnable, method: String) {
    val time1 = System.currentTimeMillis()
    for (i in 1..1_000_000) {
        runnable.run()
    }
    val time2 = System.currentTimeMillis()
    println(method + "-> " + (time2 - time1) + " ms")
}
