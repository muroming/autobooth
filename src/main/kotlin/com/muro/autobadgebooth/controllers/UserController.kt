package com.muro.autobadgebooth.controllers

import com.muro.autobadgebooth.security.JwtUtil
import com.muro.autobadgebooth.security.detailsservice.AugmentedUserDetails
import com.muro.autobadgebooth.security.jwtresponse.UserJwtResponse
import com.muro.autobadgebooth.user.data.dto.CreateUserDto
import com.muro.autobadgebooth.user.domain.interactors.UserInteractor
import com.muro.autobadgebooth.user.domain.mappers.UserMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
class UserController {

    @Autowired
    private lateinit var userInteractor: UserInteractor

    @Autowired
    private lateinit var userMapper: UserMapper

    @Autowired
    private lateinit var jwtUtil: JwtUtil

    @GetMapping("/user")
    fun getUserBadge(@RequestParam("qr_data") qr_data: String) = try {
        val userBadge = userInteractor.checkInUserWithId(qr_data)
        userBadge
                ?.let { ResponseEntity.ok(userBadge) }
                ?: ResponseEntity.notFound().build()
    } catch (e: Exception) {
        ResponseEntity.status(500).body("Internal server error")
    }

    @PostMapping("/create_user")
    fun registerUser(@Valid @RequestBody createUserDto: CreateUserDto) = try {
        val userInfo = userMapper.mapInfo(createUserDto)
        val userId = userInteractor.createUser(userInfo)
        val token = jwtUtil.generateToken(AugmentedUserDetails(userId, createUserDto.email, createUserDto.password))
        ResponseEntity.ok(UserJwtResponse(userId, createUserDto.email, token))
    } catch (e: Exception) {
        ResponseEntity.status(500).body("Internal server error")
    }
}