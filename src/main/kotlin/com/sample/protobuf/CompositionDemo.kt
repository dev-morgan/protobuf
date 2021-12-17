package com.sample.protobuf

import com.sample.models.Address
import com.sample.models.Car
import com.sample.models.Person
import mu.KLogging

class CompositionDemo {
    companion object : KLogging()

    lateinit var person: Person

    fun assemble(): CompositionDemo {
        val address = Address.newBuilder()
            .setPostbox(1234)
            .setStreet("kang-nam")
            .setCity("seoul")
            .build()

        val modelX = Car.newBuilder()
            .setMake("tesla")
            .setModel("Model X")
            .setYear(2021)
            .build()

        val model3 = Car.newBuilder()
            .setMake("tesla")
            .setModel("Model 3")
            .setYear(2020)
            .build()

        val person = Person.newBuilder()
            .setName("morgan")
            .setAge(17)
            .setAddress(address)
            .addAllCar(listOf(modelX, model3))
            .build()

        this.person = person
        return this
    }
}

fun main() {
    val demo = CompositionDemo().assemble()
    println(demo.person)
}