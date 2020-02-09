package com.muro.autobadgebooth.user.data.dto

import com.muro.autobadgebooth.user.data.entities.UserEntity
import org.springframework.stereotype.Component

@Component
class UserDtoMapper {
    fun mapEntityToDto(userEntity: UserEntity): UserBadgeDto {
        return UserBadgeDto(
                "${userEntity.firstName} ${userEntity.lastName}",
                userEntity.userOrganisation,
                userEntity.rank
        )
    }
}