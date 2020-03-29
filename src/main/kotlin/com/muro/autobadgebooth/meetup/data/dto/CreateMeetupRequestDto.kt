package com.muro.autobadgebooth.meetup.data.dto

import java.util.*

data class CreateMeetupRequestDto(
        val name: String,
        val orgId: Long,
        val startTime: Date,
        val endTime: Date,
        val place: String,
        val booths: List<Long>
)