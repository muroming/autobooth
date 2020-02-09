package com.muro.autobadgebooth.user.data.entities

import javax.persistence.*

@Entity
@Table(name = "booths")
data class BoothEntity(
        @Id
        @Column(name = "b_id")
        val id: String,

        @Column(name = "b_login")
        val accessLogin: String,

        @Column(name = "b_password")
        val accessPassword: String,
        
        @OneToOne
        @JoinColumn(name = "m_id", referencedColumnName = "m_id")
        val meetup: MeetupEntity
)