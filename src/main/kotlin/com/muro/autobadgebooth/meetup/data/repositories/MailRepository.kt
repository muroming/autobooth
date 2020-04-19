package com.muro.autobadgebooth.meetup.data.repositories

import com.muro.autobadgebooth.user.data.repositories.QrRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component
import java.io.File
import java.util.*
import javax.imageio.ImageIO

@Component
class MailRepository {
    @Autowired
    private lateinit var mailSender: JavaMailSender

    @Autowired
    private lateinit var qrRepository: QrRepository

    fun sendPasswordToUser(meetupName: String, email: String, password: String) {
        SimpleMailMessage().apply {
            setTo(email)
            setSubject("${meetupName.capitalize()} meetup. Password for booths")
            setText("Use password\n$password\nto access your booths")
            mailSender.send(this)
        }
    }

    fun sendQrToUser(email: String, id: String) {
        val qr = qrRepository.createQrCode(id)
        val msg = mailSender.createMimeMessage()
        val tempQrFile = File("./${UUID.randomUUID()}.png").apply {
            ImageIO.write(qr, "png", this)
        }
        MimeMessageHelper(msg, true).apply {
            setTo(email)
            setSubject("Event QR registration")
            setText("Use this QR code to register yourself on the event\nInput this id $id if failed to scan QR")
            addAttachment("qr.png", tempQrFile)
        }

        mailSender.send(msg)
        tempQrFile.delete()
    }
}