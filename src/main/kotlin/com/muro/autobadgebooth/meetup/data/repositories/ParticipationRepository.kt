package com.muro.autobadgebooth.meetup.data.repositories

import com.muro.autobadgebooth.meetup.data.mappers.TalkMapper
import com.muro.autobadgebooth.meetup.domain.entities.TalkInfo
import com.muro.autobadgebooth.user.data.datasources.ParticipationDatabaseJpa
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ParticipationRepository {
    @Autowired
    private lateinit var participationDatabase: ParticipationDatabaseJpa

    @Autowired
    private lateinit var talkMapper: TalkMapper

    fun createTalk(talk: TalkInfo): Long {
        val participation = talkMapper.mapParticipationEntity(talk)

        return participationDatabase.saveAndFlush(participation).id
    }
}