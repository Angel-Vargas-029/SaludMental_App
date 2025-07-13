package com.example.saludmental.data.models

import java.util.Date

data class DiaryEntry(
    val id: String = "",
    val title: String,
    val content: String,
    val date: Date = Date(),
    val preview: String = content.take(50) + if (content.length > 50) "..." else ""
)
