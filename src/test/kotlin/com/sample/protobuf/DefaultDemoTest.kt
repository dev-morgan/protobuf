package com.sample.protobuf

import com.sample.models.Person
import io.kotlintest.matchers.types.shouldNotBeNull
import io.kotlintest.specs.FunSpec
import mu.KLogging

class DefaultDemoTest : FunSpec({
    test("default Value") {
        val person = Person.newBuilder().build()

        person.address.city.shouldNotBeNull() // null safe

        logger.info("City : " + person.address.city)
        logger.info("hasAddress : " + person.hasAddress())
    }
}) {
    companion object : KLogging()
}
