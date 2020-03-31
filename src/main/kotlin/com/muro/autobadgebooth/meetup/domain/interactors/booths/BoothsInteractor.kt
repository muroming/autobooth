package com.muro.autobadgebooth.meetup.domain.interactors.booths

import com.muro.autobadgebooth.meetup.domain.entities.BoothEntity

interface BoothsInteractor {
    fun getAvailableBooths(from: Long, to: Long): List<BoothEntity>
    fun setIpForBoothWithId(id: Long, ip: String)
}