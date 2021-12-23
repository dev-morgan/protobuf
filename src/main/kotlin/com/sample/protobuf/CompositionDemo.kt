package com.sample.protobuf

import com.google.protobuf.Int32Value
import com.sample.models.Address
import com.sample.models.Car
import com.sample.models.Person

fun main() {
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
        .setAge(Int32Value.newBuilder().setValue(20).build())
        .addAllCar(listOf(modelX, model3))
        .setAddress(address)
        .build()

    println(person)
    println(person.hasAge())
}
