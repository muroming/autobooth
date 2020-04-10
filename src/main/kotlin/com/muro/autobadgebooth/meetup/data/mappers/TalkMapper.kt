package com.muro.autobadgebooth.meetup.data.mappers

import com.muro.autobadgebooth.meetup.data.datasources.MeetupDatabaseJpa
import com.muro.autobadgebooth.meetup.data.datasources.RolesDatabaseJpa
import com.muro.autobadgebooth.meetup.data.dto.CreateTalkDto
import com.muro.autobadgebooth.meetup.domain.entities.TalkInfo
import com.muro.autobadgebooth.user.data.datasources.UserDatabaseJpa
import com.muro.autobadgebooth.user.domain.entities.ParticipationEntity
import com.muro.autobadgebooth.util.PasswordGenerator
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

    @Autowired
    private lateinit var passwordGenerator: PasswordGenerator

    fun mapInfo(dto: CreateTalkDto): TalkInfo {
        return TalkInfo(
                id = "",
                user = userDatabase.getOne(dto.userId),
                meetup = meetupDatabase.getOne(dto.meetupId),
                startTime = dto.startTime,
                endTime = dto.endTime,
                room = dto.room,
                role = rolesDatabase.getOne(dto.roleId)
        )
    }

    fun mapParticipationEntity(info: TalkInfo, id: String): ParticipationEntity {
        return ParticipationEntity(
                id = id,
                speechTime = info.startTime,
                meetup = info.meetup,
                user = info.user,
                role = info.role
        )
    }
}