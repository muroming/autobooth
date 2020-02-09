package com.muro.autobadgebooth.user.data.entities

import javax.persistence.*

@Entity
@Table(name = "meetup")
data class MeetupEntity (
        @Id
        @Column(name = "m_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        @Column(name = "m_address")
        val address: String,

        @Column(name = "m_orgid")
        val organisationId: String
)