package com.muro.autobadgebooth.meetup.domain.entities

import com.muro.autobadgebooth.user.domain.entities.UserEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "meetup")
data class MeetupEntity (
        @Column(name = "m_name")
        val name: String,

        @Column(name = "m_address")
        val address: String,

        @OneToOne
        @JoinColumn(name = "m_orgid", referencedColumnName = "u_id")
        val organisator: UserEntity,

        @Column(name = "m_start_date")
        val startTime: Date,

        @Column(name = "m_end_date")
        val endTime: Date
) {

    @Id
    @Column(name = "m_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
}