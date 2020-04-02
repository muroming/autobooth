package com.muro.autobadgebooth.user.domain.entities

import java.util.*

data class UserInfo(
        val email: String,
        val password: String,
        val firstName: String,
        val secondName: String,
        val rank: String,
        val sex: Char,
        val birth: Date,
        val phone: String,
        val organization: String,
        val balance: Int
)