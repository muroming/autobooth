package com.muro.autobadgebooth.security.detailsservice

import com.muro.autobadgebooth.user.data.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class JwtUserDetailsService : UserDetailsService {

    @Autowired
    private lateinit var userRepository: UserRepository

    override fun loadUserByUsername(username: String?): UserDetails = username?.let(
            userRepository::loadUserCredentialsByLogin
    ) ?: throw UsernameNotFoundException("User not found with username: $username")
}