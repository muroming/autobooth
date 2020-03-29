package com.muro.autobadgebooth.user.domain.entities

import javax.persistence.*

@Entity
@Table(name = "roles")
data class RoleEntity(
        @Id
        @Column(name = "r_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        @Column(name = "r_name")
        val roleName: String
)