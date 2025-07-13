// app/src/main/java/com/example/saludmental/data/models/DiaryEntry.kt
package com.example.saludmental.data.models

import java.util.Date

data class DiaryEntry(
    val id: String = "",
    val title: String = "",
    val content: String = "",
    val date: Date = Date()
) {
    // Constructor sin argumentos requerido por Firestore
    constructor() : this("", "", "", Date())

    // Propiedad calculada para el preview
    val preview: String
        get() = if (content.length > 50) {
            content.take(50) + "..."
        } else {
            content
        }
}