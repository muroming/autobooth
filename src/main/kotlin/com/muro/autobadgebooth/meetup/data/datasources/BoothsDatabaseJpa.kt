package com.muro.autobadgebooth.meetup.data.datasources

import com.muro.autobadgebooth.meetup.domain.entities.BoothEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface BoothsDatabaseJpa : JpaRepository<BoothEntity, String> {
    @Query("SELECT b FROM BoothEntity b WHERE b.meetup=null")
    fun getAvailableBooths(): List<BoothEntity>
}