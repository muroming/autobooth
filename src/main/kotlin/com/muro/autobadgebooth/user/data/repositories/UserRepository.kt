package com.muro.autobadgebooth.user.data.repositories

import com.muro.autobadgebooth.security.detailsservice.AugmentedUserDetails
import com.muro.autobadgebooth.user.data.datasources.ParticipationDatabaseJpa
import com.muro.autobadgebooth.user.data.datasources.UserDatabaseJpa
import com.muro.autobadgebooth.user.domain.entities.UserEntity
import com.muro.autobadgebooth.user.domain.entities.UserInfo
import com.muro.autobadgebooth.user.domain.mappers.UserMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component

@Component
class UserRepository {
    @Autowired
    lateinit var userDatabase: UserDatabaseJpa

    @Autowired
    lateinit var participationDatabase: ParticipationDatabaseJpa

    @Autowired
    lateinit var userMapper: UserMapper

    fun getUserById(userId: Long): UserEntity = userDatabase.getOne(userId)

    fun checkInUserWithId(participationId: String): UserEntity? {
        val participation = participationDatabase.findByIdOrNull(participationId)
        return participation?.user
    }

    fun loadUserCredentialsByLogin(login: String): User =
            userDatabase.findUserByEmail(login)?.let {
                AugmentedUserDetails(it.id, it.email, it.password)
            } ?: throw IllegalArgumentException("User with login $login is not found")

    fun createUser(userInfo: UserInfo): Long {
        val entity = userMapper.mapEntity(userInfo)
        return userDatabase.saveAndFlush(entity).id
    }
}