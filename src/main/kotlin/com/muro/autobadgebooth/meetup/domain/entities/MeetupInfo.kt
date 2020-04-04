package com.muro.autobadgebooth.meetup.domain.entities

import java.util.*

data class MeetupInfo(
        val name: String,
        val orgId: Long,
        val startTime: Date,
        val endTime: Date,
        val place: String,
        val booths: Int
)