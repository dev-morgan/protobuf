package com.sample.protobuf

import com.sample.models.BodyStyle
import com.sample.models.Car
import com.sample.models.Dealer

class MapDemo {
    lateinit var dealer: Dealer

    fun assemble(): MapDemo {
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
        this.dealer = dealer
        return this
    }
}

fun main() {
    val map = MapDemo().assemble()
    println(map.dealer.getModelOrThrow(2020).bodyStyle)
    println("------")
    println(map.dealer.modelMap)
    println("------")
    println(map.dealer.modelCount)
}