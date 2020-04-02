package com.muro.autobadgebooth.meetup.domain.interactors.meetup

import com.muro.autobadgebooth.meetup.data.repositories.BoothsRepository
import com.muro.autobadgebooth.meetup.data.repositories.MeetupRepository
import com.muro.autobadgebooth.meetup.data.repositories.ParticipationRepository
import com.muro.autobadgebooth.meetup.domain.entities.MeetupInfo
import com.muro.autobadgebooth.meetup.domain.entities.TalkInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MeetupInteractorImpl : MeetupInteractor {

    @Autowired
    private lateinit var meetupRepository: MeetupRepository

    @Autowired
    private lateinit var boothsRepository: BoothsRepository

    @Autowired
    private lateinit var participationRepository: ParticipationRepository

    override fun createMeetup(meetup: MeetupInfo): Long {
        val meetupId = meetupRepository.createMeetup(meetup)
        boothsRepository.assignBoothsToMeetup(meetupId, meetup.booths)

        return meetupId
    }

    override fun createTalk(talk: TalkInfo) = participationRepository.createTalk(talk)

    override fun getEvents(): List<MeetupInfo> = meetupRepository.getEvents()
}