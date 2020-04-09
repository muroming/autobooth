package com.muro.autobadgebooth.printing

import com.muro.autobadgebooth.meetup.data.datasources.BoothsDatabaseJpa
import org.cups4j.CupsClient
import org.cups4j.PrintJob
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.io.DataInputStream
import java.net.ServerSocket
import java.net.URL
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import javax.annotation.PostConstruct

@Service
class PrintingService {
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
            println("Accepted connection from ${socket.inetAddress}")
            threadPoolExecutor.execute {
                socket.use {
                    DataInputStream(it.getInputStream()).use(::printInBooth)
                }
            }
        }
    }

    @PostConstruct
    fun start() {
        thread.start()
        println("Printer service is up")
    }

    fun printInBooth(inputStream: DataInputStream): PrintingStatus {
        val boothId = inputStream.readUTF()
        println("Received boothId $boothId")
        val printerName = boothsDatabase.findByIdOrNull(boothId)?.printerUrl ?: return PrintingStatus.FAIL
        val printerAddress = URL("$LOCAL_CUPS_ADDRESS$printerName")
        println("Print address is $printerAddress")

        val cupsPrinter = cupsClient.getPrinter(printerAddress)
        println("Cups connected to printer")
        val documentSize = inputStream.readInt()
        println("Received documentSize of $documentSize bytes")
        val documentBytes = inputStream.readBytes(documentSize)
        println("Received document")
        val printJob = PrintJob.Builder(documentBytes)
                .pageFormat("iso-a6")
                .build()
        cupsPrinter.print(printJob)
        println("Started printing")

        return PrintingStatus.STARTED
    }

    companion object {
        private const val DEFAULT_SOCKET_PORT = 7878

        private const val CORE_THREAD_POOL_SIZE = 256
        private const val MAX_THREAD_POOL_SIZE = 1024
        private const val DEFAULT_KEEP_ALIVE_TIME = 10L

        private const val LOCAL_CUPS_ADDRESS = "http://localhost:631/printers/"
    }
}