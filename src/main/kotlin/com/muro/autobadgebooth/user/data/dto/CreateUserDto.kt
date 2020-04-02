package com.muro.autobadgebooth.user.data.dto

import java.util.*

data class CreateUserDto(
        val email: String,
        val password: String,
        val firstName: String,
        val secondName: String,
        val rank: String? = null,
        val sex: Char,
        val birth: Date,
        val phoneNumber: String,
        val organization: String
)