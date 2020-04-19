package com.muro.autobadgebooth.security.detailsservice

import org.springframework.security.core.userdetails.User

class AugmentedUserDetails(
        val id: Long,
        val firstName: String,
        val secondName: String,
        username: String,
        password: String
) : User(username, password, emptyList())