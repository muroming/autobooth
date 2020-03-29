package com.muro.autobadgebooth.util

import org.springframework.stereotype.Component

@Component
class PasswordGenerator {

    private val charPool = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    fun generatePassword(length: Int): String = (0 until length).fold("") { acc, _ -> acc + charPool.random() }
}