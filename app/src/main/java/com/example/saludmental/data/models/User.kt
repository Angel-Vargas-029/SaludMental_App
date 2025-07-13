package com.example.saludmental.data.models

data class User(
    val uid: String = "",
    val name: String = "",
    val email: String = "",
    val birthDate: String = "",
    val createdAt: Long = System.currentTimeMillis()
)