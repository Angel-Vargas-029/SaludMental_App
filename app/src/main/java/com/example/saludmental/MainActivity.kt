// ARCHIVO: app/src/main/java/com/example/saludmental/MainActivity.kt
package com.example.saludmental

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.core.view.WindowCompat // ¡IMPORTANTE: Añadir esta importación!
import com.example.saludmental.navigation.NavGraph
import com.example.saludmental.ui.theme.SaludMentalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // *** CAMBIO CLAVE AQUÍ ***
        // Configura la ventana para que el contenido se extienda detrás de las barras del sistema.
        // Esto hace que las barras de estado y navegación sean transparentes.
        // Esta línea solo va aquí y una sola vez.
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            SaludMentalTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    // Asegúrate de que tu NavGraph o el composable de nivel superior
                    // use Modifier.systemBarsPadding() para evitar que el contenido
                    // quede debajo de las barras del sistema.
                    NavGraph()
                }
            }
        }
    }
}
