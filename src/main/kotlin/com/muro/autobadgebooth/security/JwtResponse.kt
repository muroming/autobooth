package com.muro.autobadgebooth.security

data class JwtResponse(
        val token: String
) {
    companion object {
        private const val serialVersionUID = 1L
    }
}
