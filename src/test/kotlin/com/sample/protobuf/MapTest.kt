package com.sample.protobuf

import com.sample.models.BodyStyle
import com.sample.models.Car
import com.sample.models.Dealer
import io.kotlintest.shouldBe
import io.kotlintest.specs.FunSpec
import mu.KLogging

class MapTest : FunSpec({
    test("map") {
        val model3 = Car.newBuilder().apply {
            make = "tesla"
            model = "Model 3"
            bodyStyle = BodyStyle.SEDAN
            year = 2020
        }.build()

        val modelX = Car.newBuilder().apply {
            make = "tesla"
            model = "Model X"
            bodyStyle = BodyStyle.SUV
            year = 2021
        }.build()

        val dealer = Dealer.newBuilder()
            .putAllModel(mapOf(2020 to model3, 2021 to modelX))
            .build()

        dealer.modelCount.shouldBe(2)
        dealer.getModelOrThrow(2020).bodyStyle.shouldBe(BodyStyle.SEDAN)
        logger.info { dealer.modelMap }
    }
}) {
    companion object : KLogging()
}
