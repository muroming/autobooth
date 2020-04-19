package com.muro.autobadgebooth.meetup.data.repositories

import com.muro.autobadgebooth.meetup.data.datasources.BoothsDatabaseJpa
import com.muro.autobadgebooth.meetup.data.datasources.MeetupDatabaseJpa
import com.muro.autobadgebooth.meetup.domain.entities.BoothEntity
import com.muro.autobadgebooth.util.PasswordGenerator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import java.util.*

@Component
class BoothsRepository {
    @Autowired
    private lateinit var boothsDatabase: BoothsDatabaseJpa

    @Autowired
    private lateinit var meetupDatabase: MeetupDatabaseJpa

    @Autowired
    private lateinit var passwordGenerator: PasswordGenerator

    @Autowired
    private lateinit var passwordEncoder: BCryptPasswordEncoder

    fun getAvailableBooths(from: Date, to: Date): List<BoothEntity> {
        return boothsDatabase.getAvailableBooths()
    }

    fun assignBoothsToMeetup(meetupId: Long, boothIds: List<String>): String {
        val password = passwordGenerator.generatePassword(DEFAULT_PASSWORD_LENGTH)
        val meetup = meetupDatabase.getOne(meetupId)

        boothIds.forEach { id ->
            boothsDatabase.findById(id).get().copy(
                    accessPassword = passwordEncoder.encode(password),
                    meetup = meetup
            ).let(boothsDatabase::saveAndFlush)
        }

        return password
    }

    fun loadBoothDetailsById(id: String): UserDetails? {
        val booth = boothsDatabase.findByIdOrNull(id)
        return booth?.let { User(it.id, it.accessPassword, emptyList()) }
    }

    fun setPrinterIpForBooth(id: String, ip: String) {
        val booth = boothsDatabase.getOne(id).copy(printerUrl = ip)
        boothsDatabase.saveAndFlush(booth)
    }

    companion object {
        private const val DEFAULT_PASSWORD_LENGTH = 10
    }
}