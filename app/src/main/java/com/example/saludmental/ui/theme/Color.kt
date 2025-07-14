// ARCHIVO: app/src/main/java/com/example/saludmental/ui/theme/Color.kt

package com.example.saludmental.ui.theme

import androidx.compose.ui.graphics.Color

// === PALETA PRINCIPAL ===
// Inspirada en tranquilidad, crecimiento y bienestar emocional

// Verde Sereno - Color principal (tranquilidad, crecimiento)
val SerenityGreen = Color(0xFF4A7C59)  // Más profesional que el original
val SerenityGreenLight = Color(0xFF7FB069)
val SerenityGreenDark = Color(0xFF2D5233)

// Azul Calma - Color secundario (confianza, estabilidad)
val CalmBlue = Color(0xFF5B9BD5)
val CalmBlueLight = Color(0xFF8BBEE8)
val CalmBlueDark = Color(0xFF3A7BC8)

// Lavanda Mindful - Color terciario (relajación, mindfulness)
val MindfulLavender = Color(0xFF9B8CDF)
val MindfulLavenderLight = Color(0xFFB8AEEF)
val MindfulLavenderDark = Color(0xFF7B6BB8)

// === COLORES NEUTROS ===
val WarmWhite = Color(0xFFFAFAF8)      // Blanco cálido
val SoftGray = Color(0xFFF5F5F3)       // Gris muy suave
val CloudGray = Color(0xFFE8E8E6)      // Gris nube
val StoneGray = Color(0xFFB8B8B6)      // Gris piedra
val CharcoalGray = Color(0xFF3C4043)   // Gris carbón
val DeepCharcoal = Color(0xFF1A1C1E)   // Negro suave

// === COLORES EMOCIONALES ===
// Para estados de ánimo y feedback
val HappyYellow = Color(0xFFFFD56B)     // Alegría
val CalmMint = Color(0xFF7ED7C1)        // Calma
val EnergyOrange = Color(0xFFFF8F65)    // Energía
val FocusIndigo = Color(0xFF6B73D9)     // Concentración

// === COLORES DE ESTADO ===
val SuccessGreen = Color(0xFF4CAF50)    // Éxito
val WarningAmber = Color(0xFFFF9800)    // Advertencia  
val ErrorRed = Color(0xFFE57373)        // Error (suave para no generar ansiedad)
val InfoBlue = Color(0xFF42A5F5)        // Información

// === GRADIENTES ===
// Para elementos especiales y cards destacadas
val SerenityGradientStart = Color(0xFF7FB069)
val SerenityGradientEnd = Color(0xFF5B9BD5)

val CalmGradientStart = Color(0xFF8BBEE8)
val CalmGradientEnd = Color(0xFFB8AEEF)

val WarmGradientStart = Color(0xFFFAFAF8)
val WarmGradientEnd = Color(0xFFF5F5F3)

// === COLORES LEGACY (mantener compatibilidad) ===
val Purple80 = MindfulLavenderLight    // Mapear a nuestro sistema
val PurpleGrey80 = StoneGray
val Pink80 = MindfulLavender

val Purple40 = SerenityGreen           // Mapear a verde principal
val PurpleGrey40 = CharcoalGray
val Pink40 = CalmBlue