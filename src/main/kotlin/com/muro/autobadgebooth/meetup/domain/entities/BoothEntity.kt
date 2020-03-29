package com.muro.autobadgebooth.meetup.domain.entities

import javax.persistence.*

@Entity
@Table(name = "booths")
data class BoothEntity(
        @Id
        @Column(name = "b_id")
        val id: Long,

        @Column(name = "b_login")
        val accessLogin: String,

        @Column(name = "b_password")
        val accessPassword: String,

        @Column(name = "b_printer")
        val printerUrl: String,

        @OneToOne
        @JoinColumn(name = "b_meetup", referencedColumnName = "m_id")
        val meetup: MeetupEntity?
)