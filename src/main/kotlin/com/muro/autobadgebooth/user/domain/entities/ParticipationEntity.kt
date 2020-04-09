package com.muro.autobadgebooth.user.domain.entities

import javax.persistence.*

@Entity
@Table(name = "participation")
data class ParticipationEntity(

        @EmbeddedId
        val id: ParticipationId,

        @Column(name = "p_id")
        val participationToken: String,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "r_id", referencedColumnName = "r_id")
        val role: RoleEntity
)