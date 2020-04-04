package com.muro.autobadgebooth.controllers

import com.muro.autobadgebooth.meetup.data.dto.CreateMeetupDto
import com.muro.autobadgebooth.meetup.data.dto.CreateTalkDto
import com.muro.autobadgebooth.meetup.data.mappers.MeetupMapper
import com.muro.autobadgebooth.meetup.data.mappers.TalkMapper
import com.muro.autobadgebooth.meetup.domain.interactors.meetup.MeetupInteractor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class MeetupController {

    @Autowired
    private lateinit var meetupInteractor: MeetupInteractor

    @Autowired
    private lateinit var meetupMapper: MeetupMapper

    @Autowired
    private lateinit var talkMapper: TalkMapper

    @GetMapping("/events")
    fun getEvents() = try {
        val events = meetupInteractor.getEvents()
        ResponseEntity.ok(events)
    } catch (e: Exception) {
        ResponseEntity.status(500).body("Internal server error")
    }

    @PostMapping("/event/create")
    fun createEvent(@Valid @RequestBody createMeetupDto: CreateMeetupDto) = try {
        val meetupInfo = meetupMapper.mapMeetupInfo(createMeetupDto)
        val meetupId = meetupInteractor.createMeetup(meetupInfo)

        ResponseEntity.ok(meetupId)
    } catch (e: Exception) {
        ResponseEntity.status(500).body("Internal server error")
    }

    @PostMapping("/event/create_talk")
    fun createTalk(@Valid @RequestBody createTalkDto: CreateTalkDto) = try {
        val talkInfo = talkMapper.mapInfo(createTalkDto)
        val id = meetupInteractor.createTalk(talkInfo)

        ResponseEntity.ok(id)
    } catch (e: Exception) {
        ResponseEntity.status(500).body("Internal server error")
    }

    @GetMapping("/event/register_user")
    fun registerUserForEvent(
            @RequestParam("userId") userId: Long,
            @RequestParam("meetupId") meetupId: Long
    ) = try {
        val id = meetupInteractor.registerUserForMeetup(userId, meetupId)

        ResponseEntity.ok(id)
    } catch (e: Exception) {
        ResponseEntity.status(500).body("Internal server error")
    }
}