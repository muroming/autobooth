package com.muro.autobadgebooth.user.data.dto

import com.muro.autobadgebooth.user.domain.entities.UsersEntity
import org.springframework.stereotype.Component

@Component
class UserDtoMapper {
    fun mapEntityToDto(userEntity: UsersEntity): UserBadgeDto {
        return UserBadgeDto(
                "${userEntity.firstName} ${userEntity.lastName}",
                userEntity.userOrganisation,
                userEntity.rank
        )
    }
}