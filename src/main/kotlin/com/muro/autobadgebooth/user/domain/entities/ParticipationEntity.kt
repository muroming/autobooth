package com.muro.autobadgebooth.user.domain.entities

import javax.persistence.*

@Entity
@Table(name = "participation")
data class ParticipationEntity(
        @Column(name = "p_id")
        val id: String,

        @EmbeddedId
        val key: ParticipationId,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "r_id", referencedColumnName = "r_id")
        val role: RoleEntity
)