package com.muro.autobadgebooth.user.data.dto

data class UserBadgeDto(
        val userName: String,
        val userOrganization: String,
        val userPayload: String = ""
)