package com.muro.autobadgebooth.meetup.domain.interactors.meetup

import com.muro.autobadgebooth.meetup.data.repositories.MeetupRepository
import com.muro.autobadgebooth.meetup.domain.entities.MeetupInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class MeetupInteractorImpl : MeetupInteractor {

    @Autowired
    private lateinit var meetupRepository: MeetupRepository

    override fun createMeetup(meetup: MeetupInfo): Long {
        return meetupRepository.createMeetup(meetup)
    }
}