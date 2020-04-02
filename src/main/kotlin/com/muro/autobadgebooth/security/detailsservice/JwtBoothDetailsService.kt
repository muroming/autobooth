package com.muro.autobadgebooth.security.detailsservice

import com.muro.autobadgebooth.meetup.data.repositories.BoothsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class JwtBoothDetailsService : UserDetailsService {

    @Autowired
    lateinit var boothsRepository: BoothsRepository

    override fun loadUserByUsername(username: String?): UserDetails = username?.let(
            boothsRepository::loadBoothDetailsById
    ) ?: throw UsernameNotFoundException("User not found with username: $username")
}