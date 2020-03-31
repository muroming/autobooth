package com.muro.autobadgebooth.meetup.domain.interactors.booths

import com.muro.autobadgebooth.meetup.data.repositories.BoothsRepository
import com.muro.autobadgebooth.meetup.domain.entities.BoothEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class BoothsInteractorImpl : BoothsInteractor {

    @Autowired
    private lateinit var boothsRepository: BoothsRepository

    override fun getAvailableBooths(from: Long, to: Long): List<BoothEntity> {
        return boothsRepository.getAvailableBooths(Date(from), Date(to))
    }

    override fun setIpForBoothWithId(id: Long, ip: String) {
        boothsRepository.setPrinterIpForBooth(id, ip)
    }
}