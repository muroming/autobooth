package com.muro.autobadgebooth.user.domain.interactors

import com.muro.autobadgebooth.user.data.dto.UserBadgeDto
import com.muro.autobadgebooth.user.data.dto.UserDtoMapper
import com.muro.autobadgebooth.user.data.repositories.QrRepository
import com.muro.autobadgebooth.user.data.repositories.UserRepository
import com.muro.autobadgebooth.user.domain.entities.UserInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserInteractorImpl : UserInteractor {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var qrRepository: QrRepository

    @Autowired
    private lateinit var userMapper: UserDtoMapper


    override fun checkInUserWithId(participationId: String): UserBadgeDto? {
        val user = userRepository.checkInUserWithId(participationId)
        return user?.let(userMapper::mapEntityToDto)
    }

    override fun createUser(userInfo: UserInfo): Long = userRepository.createUser(userInfo)
}