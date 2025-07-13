package com.example.saludmental.data.models

import java.util.Date

data class Appointment(
    val id: String,
    val title: String,
    val psychologistName: String,
    val date: Date,
    val time: String,
    val location: String,
    val isVirtual: Boolean = false,
    val virtualLink: String = "",
    val isCompleted: Boolean = false
)