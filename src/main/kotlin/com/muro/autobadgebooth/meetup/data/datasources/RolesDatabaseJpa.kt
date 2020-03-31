package com.muro.autobadgebooth.meetup.data.datasources

import com.muro.autobadgebooth.user.domain.entities.RoleEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RolesDatabaseJpa : JpaRepository<RoleEntity, Long>