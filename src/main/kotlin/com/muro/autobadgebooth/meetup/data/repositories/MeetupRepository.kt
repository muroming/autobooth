package com.muro.autobadgebooth.meetup.data.repositories

import com.muro.autobadgebooth.meetup.data.datasources.MeetupDatabaseJpa
import com.muro.autobadgebooth.meetup.data.mappers.MeetupMapper
import com.muro.autobadgebooth.meetup.domain.entities.MeetupInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MeetupRepository {

    @Autowired
    private lateinit var meetupDatabase: MeetupDatabaseJpa

    @Autowired
    private lateinit var mapper: MeetupMapper

    fun createMeetup(meetupInfo: MeetupInfo): Long {
        return mapper.mapMeetupEntity(meetupInfo).let(meetupDatabase::save).id
    }

}