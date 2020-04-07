package com.muro.autobadgebooth.security

import com.muro.autobadgebooth.security.detailsservice.AugmentedUserDetails
import com.muro.autobadgebooth.security.detailsservice.JwtBoothDetailsService
import com.muro.autobadgebooth.security.detailsservice.JwtUserDetailsService
import com.muro.autobadgebooth.security.jwtresponse.BoothJwtResponse
import com.muro.autobadgebooth.security.jwtresponse.UserJwtResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@CrossOrigin
class JwtAuthenticationController {
    @Autowired
    @Qualifier("userAuthentication")
    private lateinit var userAuthenticationManager: AuthenticationManager

    @Autowired
    @Qualifier("boothAuthentication")
    private lateinit var boothAuthenticationManager: AuthenticationManager

    @Autowired
    private lateinit var jwtUtil: JwtUtil

    @Autowired
    private lateinit var userDetailsService: JwtUserDetailsService

    @Autowired
    private lateinit var boothDetailsService: JwtBoothDetailsService

    @PostMapping("/user/authenticate")
    fun authenticateUser(@RequestBody authenticationRequest: JwtUserRequest): ResponseEntity<*> {
        return try {
            println("${authenticationRequest.username} ${authenticationRequest.userPassword}")
            authenticate(userAuthenticationManager, authenticationRequest.username, authenticationRequest.userPassword)
            val userDetails = userDetailsService.loadUserByUsername(authenticationRequest.username) as AugmentedUserDetails
            val token = jwtUtil.generateToken(userDetails)
            ResponseEntity.ok(UserJwtResponse(userDetails.id, userDetails.username, token))
        } catch (e: DisabledException) {
            ResponseEntity.of(Optional.of("USER_DISABLED"))
        } catch (e: BadCredentialsException) {
            ResponseEntity.of(Optional.of("INVALID_CREDENTIALS"))
        }
    }

    @PostMapping("/booth/authenticate")
    fun authenticateBooth(@RequestBody authenticationRequest: JwtBoothRequest): ResponseEntity<*> {
        return try {
            authenticate(boothAuthenticationManager, authenticationRequest.boothId, authenticationRequest.userPassword)
            val userDetails = boothDetailsService.loadUserByUsername(authenticationRequest.boothId)
            val token = jwtUtil.generateToken(userDetails)
            ResponseEntity.ok(BoothJwtResponse(token))
        } catch (e: DisabledException) {
            ResponseEntity.of(Optional.of("USER_DISABLED"))
        } catch (e: BadCredentialsException) {
            ResponseEntity.of(Optional.of("INVALID_CREDENTIALS"))
        }
    }

    private fun authenticate(manager: AuthenticationManager, username: String, password: String) {
        manager.authenticate(UsernamePasswordAuthenticationToken(username, password))
    }
}