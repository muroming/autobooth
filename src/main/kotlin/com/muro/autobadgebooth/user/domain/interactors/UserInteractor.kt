package com.muro.autobadgebooth.user.domain.interactors

import com.muro.autobadgebooth.user.data.dto.UserBadgeDto

interface UserInteractor {
    fun checkInUserWithId(userId: Long): UserBadgeDto
}