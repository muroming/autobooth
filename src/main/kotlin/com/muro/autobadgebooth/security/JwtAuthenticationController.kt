package com.muro.autobadgebooth.security

import com.muro.autobadgebooth.security.detailsservice.JwtUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@CrossOrigin
class JwtAuthenticationController {
    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var jwtUtil: JwtUtil

    @Autowired
    private lateinit var userDetailsService: JwtUserDetailsService

    @RequestMapping("/user/authenticate", method = [RequestMethod.POST])
    fun authenticateUser(@RequestBody authenticationRequest: JwtRequest): ResponseEntity<*> {
        return try {
            authenticate(authenticationRequest.username, authenticationRequest.userPassword)
            val userDetails = userDetailsService.loadUserByUsername(authenticationRequest.username)
            val token = jwtUtil.generateToken(userDetails)
            ResponseEntity.ok(JwtResponse(token))
        } catch (e: DisabledException) {
            ResponseEntity.of(Optional.of("USER_DISABLED"))
        } catch (e: BadCredentialsException) {
            ResponseEntity.of(Optional.of("INVALID_CREDENTIALS"))
        }
    }

    @RequestMapping("/booth/authenticate", method = [RequestMethod.POST])
    fun authenticateBooth(@RequestBody authenticationRequest: JwtRequest): ResponseEntity<*> {
        return try {
            authenticate(authenticationRequest.username, authenticationRequest.userPassword)
            val userDetails = userDetailsService.loadUserByUsername(authenticationRequest.username)
            val token = jwtUtil.generateToken(userDetails)
            ResponseEntity.ok(JwtResponse(token))
        } catch (e: DisabledException) {
            ResponseEntity.of(Optional.of("USER_DISABLED"))
        } catch (e: BadCredentialsException) {
            ResponseEntity.of(Optional.of("INVALID_CREDENTIALS"))
        }
    }

    private fun authenticate(username: String?, password: String?) {
        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password))
    }
}