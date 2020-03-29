package com.muro.autobadgebooth.meetup.data.dto

import java.util.*

data class CreateMeetupRequestDto(
        val name: String,
        val startTime: Date,
        val endTime: Date,
        val place: String,
        val booths: List<Long>
)