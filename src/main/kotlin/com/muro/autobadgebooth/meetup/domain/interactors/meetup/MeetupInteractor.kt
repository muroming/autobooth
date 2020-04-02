package com.muro.autobadgebooth.meetup.domain.interactors.meetup

import com.muro.autobadgebooth.meetup.domain.entities.MeetupInfo
import com.muro.autobadgebooth.meetup.domain.entities.TalkInfo

interface MeetupInteractor {
    fun createMeetup(meetup: MeetupInfo): Long
    fun createTalk(talk: TalkInfo): Long
    fun getEvents(): List<MeetupInfo>
}