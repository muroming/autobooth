package com.muro.autobadgebooth.security

import java.io.Serializable

data class JwtRequest(
        val username: String,
        val userPassword: String
) : Serializable {
    companion object {
        private const val serialVersionUID = 1L
    }
}
