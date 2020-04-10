package com.muro.autobadgebooth.meetup.domain.interactors.meetup

import com.muro.autobadgebooth.meetup.data.repositories.BoothsRepository
import com.muro.autobadgebooth.meetup.data.repositories.MailRepository
import com.muro.autobadgebooth.meetup.data.repositories.MeetupRepository
import com.muro.autobadgebooth.meetup.data.repositories.ParticipationRepository
import com.muro.autobadgebooth.meetup.domain.entities.BoothEntity
import com.muro.autobadgebooth.meetup.domain.entities.MeetupInfo
import com.muro.autobadgebooth.meetup.domain.entities.TalkInfo
import com.muro.autobadgebooth.user.data.repositories.QrRepository
import com.muro.autobadgebooth.user.data.repositories.UserRepository
import com.muro.autobadgebooth.util.takeExactly
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

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var qrRepository: QrRepository

    @Autowired
    private lateinit var mailRepository: MailRepository

    override fun createMeetup(info: MeetupInfo): Long {
        val meetup = meetupRepository.createMeetup(info)
        val availableBooths = boothsRepository.getAvailableBooths(meetup.startTime, meetup.endTime)
                .takeExactly(info.booths)
                .map(BoothEntity::id)

        val password = boothsRepository.assignBoothsToMeetup(meetup.id, availableBooths)
        mailRepository.sendPasswordToUser(meetup.name, meetup.organisator.email, password)

        return meetup.id
    }

    override fun createTalk(talk: TalkInfo): String {
        val id = participationRepository.createTalk(talk)
        sendParticipationQrToUser(talk.user.id, id)
        return id
    }

    override fun getEvents(): List<MeetupInfo> = meetupRepository.getEvents()

    override fun registerUserForMeetup(userId: Long, meetupId: Long): String {
        val id = participationRepository.registerUserForMeetup(userId, meetupId)
        sendParticipationQrToUser(userId, id)
        return id
    }

    private fun sendParticipationQrToUser(userId: Long, participationId: String) {
        val qr = qrRepository.createQrCode(participationId)
        val user = userRepository.getUserById(userId)
        mailRepository.sendQrToUser(user.email, qr)
    }
}