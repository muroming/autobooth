package com.muro.autobadgebooth.user.data.repositories

import com.muro.autobadgebooth.user.data.datasources.UserDatabaseJpa
import com.muro.autobadgebooth.user.domain.entities.UsersEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component

@Component
class UserRepository {
    @Autowired
    lateinit var userDatabaseJpa: UserDatabaseJpa

    fun getUserById(userId: Long): UsersEntity {
        return userDatabaseJpa.findById(userId).orElseThrow {
            IllegalArgumentException("User with ID $userId is not found")
        }
    }

    fun loadUserCredentialsByLogin(login: String): User = //User("test", "\$2y\$12\$orRF7j8lAx45H4GF/iSVfe/IZcGtSRDXUS71nY/VIZUZmDUc/t.r2", emptyList())
            userDatabaseJpa.findUserByEmail(login)?.let {
                User(it.email, it.password, emptyList())
            } ?: throw IllegalArgumentException("User with login $login is not found")
}