package com.sample.protobuf

import com.sample.models.Credentials
import com.sample.models.Credentials.ModeCase.EMAILMODE
import com.sample.models.Credentials.ModeCase.PHONEMODE
import com.sample.models.EmailCredentials
import com.sample.models.PhoneOTP

fun main() {
    val emailCredentials = EmailCredentials.newBuilder()
        .setEmail("morgan@dunamu.com")
        .setPassword("1234")
        .build()

    val phoneOTP = PhoneOTP.newBuilder()
        .setNumber(112312312)
        .setCode(456)
        .build()

    val credentials = Credentials.newBuilder()
        .setPhoneMode(phoneOTP)
        .build()

    login(credentials)
}

fun login(credentials: Credentials) {

    when (credentials.modeCase) {

        EMAILMODE -> println(credentials.emailMode)
        PHONEMODE -> println(credentials.phoneMode)
        else -> {
            println("unknown mode")
        }
    }
}
