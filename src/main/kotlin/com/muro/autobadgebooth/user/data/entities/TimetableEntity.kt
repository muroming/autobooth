package com.muro.autobadgebooth.user.data.entities

import java.util.*
import javax.persistence.Column
import javax.persistence.JoinColumn
import javax.persistence.OneToOne

data class TimetableEntity(
        @Column(name = "t_date")
        val scheduleDate: Date,

        @Column(name = "t_time")
        val scheduleTime: Date,

        @Column(name = "t_room")
        val roomNumber: String,

        @OneToOne
        @JoinColumn(name = "p_id", referencedColumnName = "m_id")
        val speaker: ParticipationEntity
)