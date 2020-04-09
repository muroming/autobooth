package com.muro.autobadgebooth.meetup.data.repositories

import com.muro.autobadgebooth.meetup.data.datasources.MeetupDatabaseJpa
import com.muro.autobadgebooth.meetup.data.datasources.RolesDatabaseJpa
import com.muro.autobadgebooth.meetup.data.mappers.TalkMapper
import com.muro.autobadgebooth.meetup.domain.entities.TalkInfo
import com.muro.autobadgebooth.user.data.datasources.ParticipationDatabaseJpa
import com.muro.autobadgebooth.user.data.datasources.UserDatabaseJpa
import com.muro.autobadgebooth.user.domain.entities.ParticipationEntity
import com.muro.autobadgebooth.user.domain.entities.ParticipationId
import com.muro.autobadgebooth.util.PasswordGenerator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class ParticipationRepository {
    @Autowired
    private lateinit var participationDatabase: ParticipationDatabaseJpa

    @Autowired
    private lateinit var meetupDatabase: MeetupDatabaseJpa

    @Autowired
    private lateinit var userDatabase: UserDatabaseJpa

    @Autowired
    private lateinit var rolesDatabase: RolesDatabaseJpa

    @Autowired
    private lateinit var passwordGenerator: PasswordGenerator

    @Autowired
    private lateinit var talkMapper: TalkMapper

    fun createTalk(talk: TalkInfo): String {
        val participation = talkMapper.mapParticipationEntity(talk, getParticipationId())

        return participationDatabase.saveAndFlush(participation).participationToken
    }

    fun registerUserForMeetup(userId: Long, meetupId: Long): String {
        val user = userDatabase.getOne(userId)
        val meetup = meetupDatabase.getOne(meetupId)
        val participation = ParticipationEntity(
                participationToken = getParticipationId(),
                id = ParticipationId(
                        speechTime = Date(),
                        meetup = meetup,
                        user = user
                ),
                role = rolesDatabase.getOne(LISTENER_ROLE_ID)
        )

        return participationDatabase.saveAndFlush(participation).participationToken
    }

    private fun getParticipationId() = passwordGenerator.generatePassword(DEFAULT_ID_LENGTH)

    private companion object {
        private const val LISTENER_ROLE_ID = 3L
        private const val DEFAULT_ID_LENGTH = 10
    }
}