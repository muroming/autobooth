package com.muro.autobadgebooth.security.jwtresponse

data class BoothJwtResponse(
        val token: String
) {
    companion object {
        private const val serialVersionUID = 1L
    }
}
