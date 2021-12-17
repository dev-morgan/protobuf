package com.sample.protobuf

import com.sample.models.BodyStyle
import com.sample.models.Car
import com.sample.models.Dealer

fun main() {
    val model3 = Car.newBuilder()
        .setMake("tesla")
        .setModel("Model 3")
        .setBodyStyle(BodyStyle.SEDAN)
        .setYear(2020)
        .build()
    val modelX = Car.newBuilder()
        .setMake("tesla")
        .setModel("Model X")
        .setBodyStyle(BodyStyle.SUV)
        .setYear(2021)
        .build()
    val dealer = Dealer.newBuilder()
        .putAllModel(mapOf(2020 to model3, 2021 to modelX))
        .build()

    println(dealer.getModelOrThrow(2020).bodyStyle)
    println("------")
    println(dealer.modelMap)
    println("------")
    println(dealer.modelCount)
}