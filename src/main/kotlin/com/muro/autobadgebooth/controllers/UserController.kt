package com.muro.autobadgebooth.controllers

import com.muro.autobadgebooth.user.data.dto.UserRequestDto
import com.muro.autobadgebooth.user.domain.interactors.UserInteractor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.validation.Valid
import kotlin.math.abs

@RestController
class UserController {

    @Autowired
    private lateinit var userInteractor: UserInteractor

    @RequestMapping("/user")
    fun getUserBadge(@Valid @RequestBody userRequestDto: UserRequestDto) = try {
        val userBadge = userInteractor.checkInUserWithId(userRequestDto.id ?: throw NullPointerException("Null ID"))
        if (abs(userRequestDto.timeStamp?.time ?: 0 - Date().time) < TIMESTAMP_DELTA) {
            ResponseEntity.badRequest().body("Big difference in time, check time on your device")
        } else {
            ResponseEntity.ok(userBadge)
        }
    } catch (e: IllegalStateException) {
        ResponseEntity.badRequest().body(e.localizedMessage)
    } catch (e: Exception) {
        ResponseEntity.status(500).body("Internal server error")
    }

    companion object {
        private const val TIMESTAMP_DELTA = 1000 * 60 * 3
    }
}