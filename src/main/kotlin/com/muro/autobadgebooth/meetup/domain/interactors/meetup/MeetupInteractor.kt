package com.muro.autobadgebooth.meetup.domain.interactors.meetup

import com.muro.autobadgebooth.meetup.domain.entities.MeetupInfo

interface MeetupInteractor {
    fun createMeetup(meetup: MeetupInfo): Long
}