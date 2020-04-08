package com.muro.autobadgebooth.meetup.domain.entities

import com.muro.autobadgebooth.user.domain.entities.RoleEntity
import com.muro.autobadgebooth.user.domain.entities.UserEntity
import java.util.*

data class TalkInfo(
        val id: String,
        val user: UserEntity,
        val meetup: MeetupEntity,
        val startTime: Date,
        val endTime: Date,
        val room: String,
        val role: RoleEntity
)