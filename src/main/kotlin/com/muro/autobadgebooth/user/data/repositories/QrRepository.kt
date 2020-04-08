package com.muro.autobadgebooth.user.data.repositories

import net.glxn.qrgen.QRCode
import org.springframework.stereotype.Component
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import javax.imageio.ImageIO

@Component
class QrRepository {

    fun createQrCode(participationId: String): BufferedImage {
        val stream = QRCode
                .from(participationId)
                .withSize(250, 250)
                .stream()
        val bis = ByteArrayInputStream(stream.toByteArray())

        return ImageIO.read(bis)
    }

}