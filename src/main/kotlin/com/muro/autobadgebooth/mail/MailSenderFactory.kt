package com.muro.autobadgebooth.mail

import org.springframework.context.annotation.Bean
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.stereotype.Component

@Component
class MailSenderFactory {
    @Bean
    fun getMailSender(): JavaMailSender = JavaMailSenderImpl().apply {
        host = "smtp.gmail.com"
        port = 587

        username = System.getenv(EMAIL_USER) ?: throw NullPointerException("Email user is not set")
        password = System.getenv(EMAIL_PASS) ?: throw NullPointerException("Email password is not set")

        val props = javaMailProperties
        props["mail.transport.protocol"] = "smtp"
        props["mail.smtp.auth"] = "true"
        props["mail.smtp.starttls.enable"] = "true"
        props["mail.debug"] = "true"
    }

    companion object {
        private const val EMAIL_USER = "AUTOBOOTH_EMAIL_USER"
        private const val EMAIL_PASS = "AUTOBOOTH_EMAIL_PASS"
    }
}