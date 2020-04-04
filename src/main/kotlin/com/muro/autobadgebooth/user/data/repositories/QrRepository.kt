package com.muro.autobadgebooth.user.data.repositories

import net.glxn.qrgen.QRCode
import org.springframework.stereotype.Component
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import javax.imageio.ImageIO

@Component
class QrRepository {

    fun createQrCode(userId: Long, meetupId: Long): BufferedImage {
        val stream = QRCode
                .from(String.format(QR_CODE_DATA, userId, meetupId))
                .withSize(250, 250)
                .stream()
        val bis = ByteArrayInputStream(stream.toByteArray())

        return ImageIO.read(bis)
    }

    fun getInfoFromQr(qr: String): Pair<Long, Long> {
        val (userId, meetupId) = qr.split(SPLIT_SYMBOL).take(2).map(String::toLong)
        return userId to meetupId
    }

    companion object {
        private const val SPLIT_SYMBOL = '/'
        private const val QR_CODE_DATA = "%d$SPLIT_SYMBOL%d"
    }
}