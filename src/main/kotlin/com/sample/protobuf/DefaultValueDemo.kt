package com.sample.protobuf

import com.sample.models.Person

fun main() {
    val person = Person.newBuilder().build()

    println("City : " + person.address.city) // null safe
    println("hasAddress : " + person.hasAddress())
}