package com.muro.autobadgebooth.meetup.domain.entities

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "meetup")
data class MeetupEntity (
        @Column(name = "m_name")
        val name: String,

        @Column(name = "m_address")
        val address: String,

        @Column(name = "m_start_time")
        val startTime: Date,

        @Column(name = "m_end_time")
        val endTime: Date
) {

    @Id
    @Column(name = "m_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
}