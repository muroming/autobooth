package com.muro.autobadgebooth.user.domain

import com.muro.autobadgebooth.user.data.dto.UserBadgeDto

interface UserIntreractor {
    fun checkInUserWithId(userId: Long): UserBadgeDto
}