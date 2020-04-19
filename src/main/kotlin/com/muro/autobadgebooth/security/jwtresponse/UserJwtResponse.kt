package com.muro.autobadgebooth.security.jwtresponse

data class UserJwtResponse(
        val id: Long,
        val firstName: String,
        val secondName: String,
        val token: String
)