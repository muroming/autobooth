package com.muro.autobadgebooth.meetup.data.datasources

import com.muro.autobadgebooth.meetup.domain.entities.MeetupEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MeetupDatabaseJpa : JpaRepository<MeetupEntity, Long>