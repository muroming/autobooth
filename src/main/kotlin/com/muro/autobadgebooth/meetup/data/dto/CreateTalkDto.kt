package com.muro.autobadgebooth.meetup.data.dto

import java.util.*

data class CreateTalkDto(
        val userId: Long,
        val meetupId: Long,
        val startTime: Date,
        val endTime: Date,
        val room: String,
        val roleId: Long
)