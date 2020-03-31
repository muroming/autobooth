package com.muro.autobadgebooth.controllers

import com.muro.autobadgebooth.meetup.data.dto.CreateMeetupDto
import com.muro.autobadgebooth.meetup.data.dto.CreateTalkDto
import com.muro.autobadgebooth.meetup.data.mappers.MeetupMapper
import com.muro.autobadgebooth.meetup.data.mappers.TalkMapper
import com.muro.autobadgebooth.meetup.domain.interactors.meetup.MeetupInteractor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class MeetupController {

    @Autowired
    private lateinit var meetupInteractor: MeetupInteractor

    @Autowired
    private lateinit var meetupMapper: MeetupMapper

    @Autowired
    private lateinit var talkMapper: TalkMapper

    @PostMapping("/event/create")
    fun createEvent(@Valid @RequestBody createMeetupDto: CreateMeetupDto) = try {
        val meetupInfo = meetupMapper.mapMeetupInfo(createMeetupDto)
        val meetupId = meetupInteractor.createMeetup(meetupInfo)

        ResponseEntity.ok(meetupId)
    } catch (e: Exception) {
        ResponseEntity.status(500).body("Internal server error")
    }

    @PostMapping("/create_talk")
    fun createTalk(@Valid @RequestBody createTalkDto: CreateTalkDto) = try {
        val talkInfo = talkMapper.mapInfo(createTalkDto)
        val id = meetupInteractor.createTalk(talkInfo)

        ResponseEntity.ok(id)
    } catch (e: Exception) {
        ResponseEntity.status(500).body("Internal server error")
    }
}