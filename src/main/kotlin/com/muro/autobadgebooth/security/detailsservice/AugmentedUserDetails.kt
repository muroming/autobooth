package com.muro.autobadgebooth.security.detailsservice

import org.springframework.security.core.userdetails.User

class AugmentedUserDetails(
        val id: Long,
        username: String,
        password: String
) : User(username, password, emptyList())