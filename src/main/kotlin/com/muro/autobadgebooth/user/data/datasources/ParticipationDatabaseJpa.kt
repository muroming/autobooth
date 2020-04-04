package com.muro.autobadgebooth.user.data.datasources

import com.muro.autobadgebooth.user.domain.entities.ParticipationEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ParticipationDatabaseJpa : JpaRepository<ParticipationEntity, Long> {
    @Query("SELECT p FROM ParticipationEntity p WHERE p.key.user.id=:userId and p.key.meetup.id=:meetupId")
    fun findParticipationForUser(@Param("userId") userId: Long, @Param("meetupId") meetupId: Long): Optional<ParticipationEntity>
}