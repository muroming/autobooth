package com.muro.autobadgebooth.user.data.datasources

import com.muro.autobadgebooth.user.domain.entities.UsersEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserDatabaseJpa : JpaRepository<UsersEntity, Long> {
    fun findUserByEmail(email: String): UsersEntity?
}