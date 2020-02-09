package com.muro.autobadgebooth.user.data.repositories

import com.muro.autobadgebooth.user.data.datasources.UserDatabaseJpa
import com.muro.autobadgebooth.user.data.entities.UserEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.lang.IllegalStateException

@Component
class UserRepository  {
    @Autowired
    lateinit var userDatabaseJpa: UserDatabaseJpa

    fun getUserById(userId: Long): UserEntity {
        return userDatabaseJpa.findUserById(userId)
                ?: throw IllegalStateException("User with $userId is not found")
    }
}