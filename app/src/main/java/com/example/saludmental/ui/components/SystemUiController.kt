// ARCHIVO: app/src/main/java/com/example/saludmental/ui/components/SystemUiController.kt
// REEMPLAZAR TODO EL CONTENIDO EXISTENTE

package com.example.saludmental.ui.components

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat // Asegúrate de que esta importación esté presente

/**
 * Controlador unificado para las barras del sistema (status bar y navigation bar)
 * que mantiene consistencia visual en toda la app.
 * ✅ VERSIÓN 2024 - SIN ADVERTENCIAS DE DEPRECACIÓN
 *
 * NOTA IMPORTANTE: Este composable asume que `WindowCompat.setDecorFitsSystemWindows(window, false)`
 * ya se ha llamado una vez en `MainActivity.onCreate()` para hacer las barras transparentes.
 * Su función principal ahora es controlar la apariencia de los ÍCONOS de las barras (claros u oscuros).
 */
@Composable
fun SystemUiController(
    // Estos parámetros de color ya no controlan el color de la barra en sí,
    // sino que pueden usarse para ajustar el color de fondo de tu UI si lo necesitas,
    // o simplemente se mantienen en la firma para flexibilidad si en el futuro
    // decides no usar el modo edge-to-edge.
    statusBarColor: Color = MaterialTheme.colorScheme.surface, // No se usa para el color de la barra
    navigationBarColor: Color = MaterialTheme.colorScheme.surface, // No se usa para el color de la barra
    darkIcons: Boolean = !isSystemInDarkTheme() // Controla si los iconos son claros/oscuros
) {
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            // Obtener el InsetsController para controlar la apariencia de los iconos
            val insetsController = WindowCompat.getInsetsController(window, view)

            // *** CAMBIOS CLAVE AQUÍ: Eliminar líneas deprecadas y duplicadas ***

            // Las siguientes líneas son las que causaban las advertencias y son redundantes
            // si WindowCompat.setDecorFitsSystemWindows(window, false) está en MainActivity.
            // window.statusBarColor = statusBarColor.toArgb() // DEPRECATED y no necesario
            // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //     window.navigationBarColor = navigationBarColor.toArgb() // DEPRECATED y no necesario
            // }
            // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            //     window.setDecorFitsSystemWindows(false) // DEPRECATED y duplicado
            // }
            // WindowCompat.setDecorFitsSystemWindows(window, false) // DUPLICADO de MainActivity

            // ✅ CONFIGURAR ICONOS con el método recomendado (NO DEPRECADO)
            // Esto controla si los iconos de la barra de estado (reloj, batería) son claros u oscuros.
            insetsController.isAppearanceLightStatusBars = darkIcons
            // Esto controla si los iconos de la barra de navegación (atrás, home, recientes) son claros u oscuros.
            insetsController.isAppearanceLightNavigationBars = darkIcons
        }
    }
}

/**
 * ✨ EXTENSIÓN AUXILIAR - Para configuraciones específicas por pantalla
 * Establece los iconos de las barras del sistema para que sean claros u oscuros
 * y asume que las barras son transparentes (debido a MainActivity).
 */
@Composable
fun TransparentSystemBars(
    darkIcons: Boolean = !isSystemInDarkTheme()
) {
    // Solo necesitamos llamar a SystemUiController para controlar los iconos
    SystemUiController(darkIcons = darkIcons)
}

/**
 * 🎨 EXTENSIÓN PARA PANTALLAS CON FONDOS ESPECIALES
 * Controla la apariencia de los iconos de las barras del sistema
 * basándose en el color de fondo de la pantalla.
 */
@Composable
fun ThemedSystemBars(
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    darkIcons: Boolean = !isSystemInDarkTheme()
) {
    // Solo necesitamos llamar a SystemUiController para controlar los iconos
    // Los parámetros de color aquí son para la firma, pero no afectan directamente
    // el color de la barra si es transparente.
    SystemUiController(darkIcons = darkIcons)
}
