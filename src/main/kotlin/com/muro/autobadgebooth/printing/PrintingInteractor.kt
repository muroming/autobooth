package com.muro.autobadgebooth.printing

import com.muro.autobadgebooth.meetup.data.datasources.BoothsDatabaseJpa
import org.cups4j.CupsClient
import org.cups4j.PrintJob
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.RestController
import java.io.DataInputStream
import java.net.ServerSocket
import java.net.URL
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

@RestController
class PrintingInteractor {
    @Autowired
    private lateinit var boothsDatabase: BoothsDatabaseJpa

    private val cupsClient = CupsClient()
    private val serverSocket = ServerSocket(DEFAULT_SOCKET_PORT)
    private val thread = Thread(Runnable { listen() })
    private val threadPoolExecutor = ThreadPoolExecutor(
            CORE_THREAD_POOL_SIZE,
            MAX_THREAD_POOL_SIZE,
            DEFAULT_KEEP_ALIVE_TIME,
            TimeUnit.SECONDS,
            LinkedBlockingDeque()
    )

    fun listen() {
        while (true) {
            val socket = serverSocket.accept()
            threadPoolExecutor.execute {
                socket.use {
                    DataInputStream(it.getInputStream()).use(::printInBooth)
                }
            }
        }
    }

    fun printInBooth(inputStream: DataInputStream): PrintingStatus {
        val boothId = inputStream.readUTF()
        val printerAddress = URL(boothsDatabase.findByIdOrNull(boothId)?.printerUrl ?: return PrintingStatus.FAIL)

        val cupsPrinter = cupsClient.getPrinter(printerAddress)
        val documentSize = inputStream.readInt()
        val documentBytes = inputStream.readBytes(documentSize)

        val printJob = PrintJob.Builder(documentBytes).build()
        cupsPrinter.print(printJob)

        return PrintingStatus.STARTED
    }

    companion object {
        private const val DEFAULT_SOCKET_PORT = 7878

        private const val CORE_THREAD_POOL_SIZE = 256
        private const val MAX_THREAD_POOL_SIZE = 1024
        private const val DEFAULT_KEEP_ALIVE_TIME = 10L
    }
}