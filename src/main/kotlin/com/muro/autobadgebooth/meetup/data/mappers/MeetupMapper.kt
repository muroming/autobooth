package com.muro.autobadgebooth.meetup.data.mappers

import com.muro.autobadgebooth.meetup.data.dto.CreateMeetupDto
import com.muro.autobadgebooth.meetup.domain.entities.MeetupEntity
import com.muro.autobadgebooth.meetup.domain.entities.MeetupInfo
import com.muro.autobadgebooth.user.data.datasources.UserDatabaseJpa
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MeetupMapper {

    @Autowired
    private lateinit var usersDatabase: UserDatabaseJpa

    fun mapMeetupInfo(dto: CreateMeetupDto): MeetupInfo = MeetupInfo(
            name = dto.name,
            orgId = dto.orgId,
            startTime = dto.startTime,
            endTime = dto.endTime,
            place = dto.place,
            booths = dto.booths
    )

    fun mapMeetupEntity(meetupInfo: MeetupInfo): MeetupEntity = MeetupEntity(
            name = meetupInfo.name,
            organisator = usersDatabase.findById(meetupInfo.orgId).get(),
            address = meetupInfo.place,
            startTime = meetupInfo.startTime,
            endTime = meetupInfo.endTime
    )
}