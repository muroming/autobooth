package com.muro.autobadgebooth.meetup.data.repositories

import com.muro.autobadgebooth.meetup.data.datasources.MeetupDatabaseJpa
import com.muro.autobadgebooth.meetup.data.datasources.RolesDatabaseJpa
import com.muro.autobadgebooth.meetup.data.mappers.MeetupMapper
import com.muro.autobadgebooth.meetup.domain.entities.MeetupEntity
import com.muro.autobadgebooth.meetup.domain.entities.MeetupInfo
import com.muro.autobadgebooth.user.data.datasources.ParticipationDatabaseJpa
import com.muro.autobadgebooth.user.data.datasources.UserDatabaseJpa
import com.muro.autobadgebooth.user.domain.entities.ParticipationEntity
import com.muro.autobadgebooth.user.domain.entities.ParticipationId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class MeetupRepository {

    @Autowired
    private lateinit var meetupDatabase: MeetupDatabaseJpa

    @Autowired
    private lateinit var participationDatabase: ParticipationDatabaseJpa

    @Autowired
    private lateinit var userDatabase: UserDatabaseJpa

    @Autowired
    private lateinit var rolesDatabase: RolesDatabaseJpa

    @Autowired
    private lateinit var mapper: MeetupMapper

    fun createMeetup(meetupInfo: MeetupInfo): MeetupEntity {
        return mapper.mapMeetupEntity(meetupInfo).let(meetupDatabase::save)
    }

    fun getEvents(): List<MeetupInfo> {
        return meetupDatabase.findAll().map(mapper::mapMeetupInfo)
    }

    fun registerUserForMeetup(userId: Long, meetupId: Long): Long {
        val user = userDatabase.getOne(userId)
        val meetup = meetupDatabase.getOne(meetupId)
        val particiaption = ParticipationEntity(
                key = ParticipationId(
                        speechTime = Date(),
                        meetup = meetup,
                        user = user
                ),
                role = rolesDatabase.getOne(LISTENER_ROLE_ID)
        )

        return participationDatabase.saveAndFlush(particiaption).id
    }

    private companion object {
        private const val LISTENER_ROLE_ID = 3L
    }

}