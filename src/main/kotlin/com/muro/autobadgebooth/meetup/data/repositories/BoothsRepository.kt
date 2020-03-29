package com.muro.autobadgebooth.meetup.data.repositories

import com.muro.autobadgebooth.meetup.data.datasources.BoothsDatabaseJpa
import com.muro.autobadgebooth.meetup.domain.entities.BoothEntity
import com.muro.autobadgebooth.meetup.domain.entities.MeetupEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class BoothsRepository {
    @Autowired
    private lateinit var boothsDatabase: BoothsDatabaseJpa

    fun getAvailableBooths(from: Date, to: Date): List<BoothEntity> {
        return boothsDatabase.findAll()
                .filter {
                    it.meetup == null
                            || (from.intersectsWithMeetupTime(it.meetup).not() && to.intersectsWithMeetupTime(it.meetup))

                }
    }

    private fun Date.intersectsWithMeetupTime(meetup: MeetupEntity): Boolean {
        val start = meetup.startTime
        val end = meetup.endTime

        return this.after(start) && this.before(end)
    }
}