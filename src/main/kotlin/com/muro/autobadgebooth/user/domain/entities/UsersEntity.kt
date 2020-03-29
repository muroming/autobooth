package com.muro.autobadgebooth.user.domain.entities

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
data class UsersEntity(
        @Id
        @Column(name = "u_id")
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        val id: Long,

        @Column(name = "u_mail")
        val email: String,

        @Column(name = "u_pass")
        val password: String,

        @Column(name = "u_fname")
        val firstName: String,

        @Column(name = "u_lname")
        val lastName: String,

        @Column(name = "u_rank")
        val rank: String,

        @Column(name = "u_sex")
        val sex: String,

        @Column(name = "u_birth")
        val birth: Date,

        @Column(name = "u_organisation")
        val userOrganisation: String,

        @Column(name = "u_phone")
        val phoneNumber: String,

        @Column(name = "u_balance")
        val balance: Long
)