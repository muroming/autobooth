package com.muro.autobadgebooth.controllers

import com.muro.autobadgebooth.meetup.data.dto.CreateMeetupRequestDto
import com.muro.autobadgebooth.meetup.data.mappers.MeetupMapper
import com.muro.autobadgebooth.meetup.domain.interactors.booths.BoothsInteractor
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
    private lateinit var boothsInteractor: BoothsInteractor

    @Autowired
    private lateinit var meetupMapper: MeetupMapper

    @PostMapping("/event/create")
    fun createEvent(@Valid @RequestBody createMeetupRequestDto: CreateMeetupRequestDto) = try {
        val meetupInfo = meetupMapper.mapMeetupInfo(createMeetupRequestDto)
        val meetupId = meetupInteractor.createMeetup(meetupInfo)

        ResponseEntity.ok(meetupId)
    } catch (e: Exception) {
        ResponseEntity.status(500).body("Internal server error")
    }

    @GetMapping("/booths/available")
    fun getAvailableBoothsCount(
            @RequestParam("from") fromDate: Long,
            @RequestParam("to") toDate: Long
    ) = try {
        val booths = boothsInteractor.getAvailableBooths(fromDate, toDate)
        ResponseEntity.ok(booths)
    } catch (e: Exception) {
        ResponseEntity.status(500).body("Internal server error")
    }
}