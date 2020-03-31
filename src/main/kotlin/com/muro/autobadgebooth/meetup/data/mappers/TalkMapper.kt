package com.muro.autobadgebooth.meetup.data.mappers

import com.muro.autobadgebooth.meetup.data.datasources.MeetupDatabaseJpa
import com.muro.autobadgebooth.meetup.data.datasources.RolesDatabaseJpa
import com.muro.autobadgebooth.meetup.data.dto.CreateTalkDto
import com.muro.autobadgebooth.meetup.domain.entities.TalkInfo
import com.muro.autobadgebooth.user.data.datasources.UserDatabaseJpa
import com.muro.autobadgebooth.user.domain.entities.ParticipationEntity
import com.muro.autobadgebooth.user.domain.entities.ParticipationId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TalkMapper {
    @Autowired
    private lateinit var userDatabase: UserDatabaseJpa

    @Autowired
    private lateinit var meetupDatabase: MeetupDatabaseJpa

    @Autowired
    private lateinit var rolesDatabase: RolesDatabaseJpa

    fun mapInfo(dto: CreateTalkDto): TalkInfo {
        return TalkInfo(
                user = userDatabase.getOne(dto.userId),
                meetup = meetupDatabase.getOne(dto.meetupId),
                startTime = dto.startTime,
                endTime = dto.endTime,
                room = dto.room,
                role = rolesDatabase.getOne(dto.roleId)
        )
    }

    fun mapParticipationEntity(info: TalkInfo): ParticipationEntity {
        return ParticipationEntity(
                key = createParticipationKey(info),
                role = info.role
        )
    }

    private fun createParticipationKey(info: TalkInfo): ParticipationId {
        return ParticipationId(
                speechTime = info.startTime,
                meetup = info.meetup,
                user = info.user
        )
    }
}