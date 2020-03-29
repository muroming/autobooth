package com.muro.autobadgebooth.user.domain.entities

import com.muro.autobadgebooth.meetup.domain.entities.MeetupEntity
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "participation")
data class ParticipationEntity(

        @Id
        @Column(name = "p_speech_time")
        val speechTime: Date?,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "r_id", referencedColumnName = "r_id")
        val role: RoleEntity,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "m_id", referencedColumnName = "m_id")
        val meetup: MeetupEntity,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "u_id", referencedColumnName = "u_id")
        val user: UsersEntity
)