package com.muro.autobadgebooth.meetup.data.mappers

import com.muro.autobadgebooth.meetup.data.dto.CreateMeetupRequestDto
import com.muro.autobadgebooth.meetup.domain.entities.MeetupEntity
import com.muro.autobadgebooth.meetup.domain.entities.MeetupInfo
import org.springframework.stereotype.Component

@Component
class MeetupMapper {
    fun mapMeetupInfo(dto: CreateMeetupRequestDto): MeetupInfo = MeetupInfo(
            name = dto.name,
            startTime = dto.startTime,
            endTime = dto.endTime,
            place = dto.place,
            booths = dto.booths
    )

    fun mapMeetupEntity(meetupInfo: MeetupInfo): MeetupEntity = MeetupEntity(
            name = meetupInfo.name,
            address = meetupInfo.place,
            startTime = meetupInfo.startTime,
            endTime = meetupInfo.endTime
    )
}