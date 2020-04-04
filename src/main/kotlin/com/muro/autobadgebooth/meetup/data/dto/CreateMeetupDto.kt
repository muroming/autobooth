package com.muro.autobadgebooth.meetup.data.dto

import java.util.*

data class CreateMeetupDto(
        val name: String,
        val orgId: Long,
        val startTime: Date,
        val endTime: Date,
        val place: String,
        val booths: Int
)