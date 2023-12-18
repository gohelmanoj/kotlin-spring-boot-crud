package com.example.demo.users

import jakarta.persistence.*

@Entity
@Table(name = "Sample")
data class User (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,
    val name: String,
    val email: String
)




