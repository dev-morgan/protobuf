package com.sample.protobuf

import com.google.protobuf.Int32Value
import com.sample.models.Address
import com.sample.models.Car
import com.sample.models.Person
import io.kotlintest.matchers.boolean.shouldBeTrue
import io.kotlintest.specs.FunSpec
import mu.KLogging

class CompositionTest : FunSpec({
    test("default Value") {
        val address = Address.newBuilder().apply {
            postbox = 1234
            street = "Gangnam"
            city = "Seoul"
        }.build()

        val modelX = Car.newBuilder().apply {
            make = "tesla"
            model = "Model X"
            year = 2021
        }.build()

        val model3 = Car.newBuilder().apply {
            make = "tesla"
            model = "Model 3"
            year = 2020
        }.build()

        val person = Person.newBuilder()
            .setName("morgan")
            .setAge(Int32Value.newBuilder().setValue(20).build())
            .addAllCar(listOf(modelX, model3))
            .setAddress(address)
            .build()

        logger.info { person }

        person.hasAge().shouldBeTrue()
    }
}) {
    companion object : KLogging()
}
