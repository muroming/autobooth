package com.muro.autobadgebooth.user.data.datasources

import com.muro.autobadgebooth.user.domain.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserDatabaseJpa : JpaRepository<UserEntity, Long> {
    fun findUserByEmail(email: String): UserEntity?
}