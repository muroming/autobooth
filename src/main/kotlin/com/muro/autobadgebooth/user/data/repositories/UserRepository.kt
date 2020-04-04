package com.muro.autobadgebooth.user.data.repositories

import com.muro.autobadgebooth.user.data.datasources.UserDatabaseJpa
import com.muro.autobadgebooth.user.domain.entities.UserEntity
import com.muro.autobadgebooth.user.domain.entities.UserInfo
import com.muro.autobadgebooth.user.domain.mappers.UserMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component

@Component
class UserRepository {
    @Autowired
    lateinit var userDatabase: UserDatabaseJpa

    @Autowired
    lateinit var userMapper: UserMapper

    fun getUserById(userId: Long): UserEntity = userDatabase.getOne(userId)

    fun checkInUserWithId(userId: Long, meetupId: Long) {

    }

    fun loadUserCredentialsByLogin(login: String): User =
            userDatabase.findUserByEmail(login)?.let {
                User(it.email, it.password, emptyList())
            } ?: throw IllegalArgumentException("User with login $login is not found")

    fun createUser(userInfo: UserInfo): Long {
        val entity = userMapper.mapEntity(userInfo)
        return userDatabase.saveAndFlush(entity).id
    }
}