package com.muro.autobadgebooth.meetup.data.repositories

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component
import java.awt.image.BufferedImage
import java.io.File
import java.util.*
import javax.imageio.ImageIO

@Component
class MailRepository {
    @Autowired
    private lateinit var mailSender: JavaMailSender

    fun sendPasswordToUser(meetupName: String, email: String, password: String) {
        SimpleMailMessage().apply {
            setTo(email)
            setSubject("${meetupName.capitalize()} meetup. Password for booths")
            setText("Use password\n$password\nto access your booths")
            mailSender.send(this)
        }
    }

    fun sendQrToUser(email: String, qr: BufferedImage) {
        val msg = mailSender.createMimeMessage()
        val tempQrFile = File("./${UUID.randomUUID()}.png").apply {
            ImageIO.write(qr, "png", this)
        }
        MimeMessageHelper(msg, true).apply {
            setTo(email)
            setSubject("Event QR registration")
            setText("Use this QR code to register yourself on the event")
            addAttachment("qr.png", tempQrFile)
        }

        mailSender.send(msg)
        tempQrFile.delete()
    }
}