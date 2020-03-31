package com.muro.autobadgebooth.meetup.domain.entities

import com.muro.autobadgebooth.user.domain.entities.RoleEntity
import com.muro.autobadgebooth.user.domain.entities.UsersEntity
import java.util.*

data class TalkInfo(
        val user: UsersEntity,
        val meetup: MeetupEntity,
        val startTime: Date,
        val endTime: Date,
        val room: String,
        val role: RoleEntity
)