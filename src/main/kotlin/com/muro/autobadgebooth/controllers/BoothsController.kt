package com.muro.autobadgebooth.controllers

import com.muro.autobadgebooth.meetup.domain.interactors.booths.BoothsInteractor
import com.muro.autobadgebooth.printing.PrintingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class BoothsController {

    @Autowired
    private lateinit var boothsInteractor: BoothsInteractor

    @Autowired
    private lateinit var printingService: PrintingService

    @GetMapping("/booths/available")
    fun getAvailableBooths(
            @RequestParam("from") fromDate: Long,
            @RequestParam("to") toDate: Long
    ) = try {
        val booths = boothsInteractor.getAvailableBooths(fromDate, toDate)
        ResponseEntity.ok(booths.size)
    } catch (e: Exception) {
        ResponseEntity.status(500).body("Internal server error")
    }

    @PostMapping("/booths/set_print_ip")
    fun setPrinterIpFotBooth(@RequestParam("ip") ip: String, @RequestParam("boothId") boothId: String) = try {
        val booths = boothsInteractor.setIpForBoothWithId(boothId, ip)
        ResponseEntity.ok(booths)
    } catch (e: Exception) {
        ResponseEntity.status(500).body("Internal server error")
    }
}