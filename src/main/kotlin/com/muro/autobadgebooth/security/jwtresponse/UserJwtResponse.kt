package com.muro.autobadgebooth.security.jwtresponse

data class UserJwtResponse(
        val id: Long,
        val login: String,
        val token: String
)