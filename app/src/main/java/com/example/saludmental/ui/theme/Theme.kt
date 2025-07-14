// ARCHIVO: app/src/main/java/com/example/saludmental/ui/theme/Theme.kt
// REEMPLAZAR TODO EL CONTENIDO EXISTENTE

package com.example.saludmental.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.saludmental.ui.components.SystemUiController

// === ESQUEMA DE COLORES CLARO (Modo día) ===
private val LightColorScheme = lightColorScheme(
    // Colores principales
    primary = SerenityGreen,              // Verde sereno principal
    onPrimary = WarmWhite,                // Texto sobre verde
    primaryContainer = SerenityGreenLight, // Contenedores verdes
    onPrimaryContainer = DeepCharcoal,     // Texto en contenedores

    // Colores secundarios
    secondary = CalmBlue,                  // Azul calma
    onSecondary = WarmWhite,              // Texto sobre azul
    secondaryContainer = CalmBlueLight,    // Contenedores azules
    onSecondaryContainer = DeepCharcoal,   // Texto en contenedores

    // Colores terciarios
    tertiary = MindfulLavender,           // Lavanda mindful
    onTertiary = WarmWhite,               // Texto sobre lavanda
    tertiaryContainer = MindfulLavenderLight, // Contenedores lavanda
    onTertiaryContainer = DeepCharcoal,    // Texto en contenedores

    // Colores de superficie
    background = WarmWhite,               // Fondo principal
    onBackground = DeepCharcoal,          // Texto sobre fondo
    surface = SoftGray,                   // Superficies (cards, etc)
    onSurface = CharcoalGray,             // Texto sobre superficies
    surfaceVariant = CloudGray,           // Superficies variantes
    onSurfaceVariant = CharcoalGray,      // Texto sobre variantes

    // Colores de estado
    error = ErrorRed,                     // Error suave
    onError = WarmWhite,                  // Texto sobre error
    errorContainer = Color(0xFFFFEBEE),   // Contenedor error
    onErrorContainer = Color(0xFFB71C1C), // Texto en contenedor error

    // Colores de outline
    outline = StoneGray,                  // Bordes sutiles
    outlineVariant = CloudGray,           // Bordes muy sutiles

    // Otros colores del sistema
    inverseSurface = CharcoalGray,        // Superficie invertida
    inverseOnSurface = WarmWhite,         // Texto invertido
    inversePrimary = SerenityGreenLight,   // Primary invertido
    surfaceTint = SerenityGreen,          // Tinte de superficie
    scrim = Color(0x80000000)             // Scrim (overlay)
)

// === ESQUEMA DE COLORES OSCURO (Modo noche) ===
private val DarkColorScheme = darkColorScheme(
    // Colores principales adaptados para modo oscuro
    primary = SerenityGreenLight,         // Verde más claro para contraste
    onPrimary = DeepCharcoal,             // Texto oscuro sobre verde claro
    primaryContainer = SerenityGreenDark,  // Contenedor verde oscuro
    onPrimaryContainer = SerenityGreenLight, // Texto claro en contenedor

    // Colores secundarios
    secondary = CalmBlueLight,            // Azul más claro
    onSecondary = DeepCharcoal,           // Texto oscuro sobre azul
    secondaryContainer = CalmBlueDark,     // Contenedor azul oscuro
    onSecondaryContainer = CalmBlueLight,  // Texto claro en contenedor

    // Colores terciarios
    tertiary = MindfulLavenderLight,      // Lavanda más clara
    onTertiary = DeepCharcoal,            // Texto oscuro sobre lavanda
    tertiaryContainer = MindfulLavenderDark, // Contenedor lavanda oscuro
    onTertiaryContainer = MindfulLavenderLight, // Texto claro en contenedor

    // Colores de superficie para modo oscuro
    background = Color(0xFF0F1115),       // Fondo muy oscuro pero suave
    onBackground = Color(0xFFE6E6E6),     // Texto claro sobre fondo oscuro
    surface = Color(0xFF1A1C1E),          // Superficies oscuras
    onSurface = Color(0xFFE3E3E1),        // Texto sobre superficies
    surfaceVariant = Color(0xFF2C2E30),   // Superficies variantes
    onSurfaceVariant = Color(0xFFC7C7C5), // Texto sobre variantes

    // Colores de estado para modo oscuro
    error = Color(0xFFFFB4AB),            // Error suave para modo oscuro
    onError = Color(0xFF690005),          // Texto sobre error
    errorContainer = Color(0xFF93000A),   // Contenedor error oscuro
    onErrorContainer = Color(0xFFFFDAD6), // Texto en contenedor error

    // Colores de outline para modo oscuro
    outline = Color(0xFF91918F),          // Bordes visibles pero suaves
    outlineVariant = Color(0xFF41484D),   // Bordes sutiles

    // Otros colores del sistema para modo oscuro
    inverseSurface = Color(0xFFE6E6E6),   // Superficie invertida
    inverseOnSurface = Color(0xFF1A1C1E), // Texto invertido
    inversePrimary = SerenityGreen,       // Primary invertido
    surfaceTint = SerenityGreenLight,     // Tinte de superficie
    scrim = Color(0x80000000)             // Scrim
)

@Composable
fun SaludMentalTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color está disponible en Android 12+
    // Lo deshabilitamos para usar nuestra paleta personalizada
    dynamicColor: Boolean = false,  // Cambiado a false para usar nuestros colores
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    // ✨ APLICAR COLORES CONSISTENTES A LAS BARRAS DEL SISTEMA
    SystemUiController(
        statusBarColor = colorScheme.surface,
        navigationBarColor = colorScheme.surface,
        darkIcons = !darkTheme
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}