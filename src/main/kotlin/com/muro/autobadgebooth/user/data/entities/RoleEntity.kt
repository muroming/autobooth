package com.muro.autobadgebooth.user.data.entities

import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

data class RoleEntity(
        @Id
        @Column(name = "r_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,

        @Column(name = "r_name")
        val roleName: String
)