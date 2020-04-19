package com.muro.autobadgebooth.user.data.mappers

import com.muro.autobadgebooth.user.data.dto.CreateUserDto
import com.muro.autobadgebooth.user.domain.entities.UserEntity
import com.muro.autobadgebooth.user.domain.entities.UserInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class UserMapper {

    @Autowired
    private lateinit var passwordEncoder: BCryptPasswordEncoder

    fun mapInfo(dto: CreateUserDto): UserInfo {
        return UserInfo(
                email = dto.email,
                password = dto.password,
                firstName = dto.firstName,
                secondName = dto.secondName,
                rank = dto.rank ?: "",
                sex = dto.sex,
                birth = dto.birth,
                phone = dto.phoneNumber,
                organization = dto.organization,
                balance = 0
        )
    }

    fun mapEntity(info: UserInfo): UserEntity {
        return UserEntity(
                email = info.email,
                password = passwordEncoder.encode(info.password),
                firstName = info.firstName,
                lastName = info.secondName,
                rank = info.rank,
                sex = info.sex.toString(),
                birth = info.birth,
                phoneNumber = info.phone,
                userOrganisation = info.organization,
                balance = 0
        )
    }
}