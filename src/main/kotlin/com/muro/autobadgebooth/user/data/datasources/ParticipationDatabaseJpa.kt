package com.muro.autobadgebooth.user.data.datasources

import com.muro.autobadgebooth.user.domain.entities.ParticipationEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ParticipationDatabaseJpa : JpaRepository<ParticipationEntity, Long>