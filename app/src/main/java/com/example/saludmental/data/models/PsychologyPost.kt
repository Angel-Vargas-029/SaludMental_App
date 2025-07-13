package com.example.saludmental.data.models

data class PsychologyPost(
    val id: String,
    val title: String,
    val content: String,
    val imageRes: Int? = null,
    val category: String
)
