package com.muro.autobadgebooth.user.domain.entities

import javax.persistence.*

@Entity
@Table(name = "participation")
data class ParticipationEntity(
        @EmbeddedId
        val key: ParticipationId,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "r_id", referencedColumnName = "r_id")
        val role: RoleEntity
) {

        @Column(name = "p_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0
}