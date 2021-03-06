package com.muro.autobadgebooth.user.domain.interactors

import com.muro.autobadgebooth.user.data.dto.UserBadgeDto
import com.muro.autobadgebooth.user.domain.entities.UserInfo

interface UserInteractor {
    fun checkInUserWithId(participationId: String): UserBadgeDto?
    fun createUser(userInfo: UserInfo): Long
}