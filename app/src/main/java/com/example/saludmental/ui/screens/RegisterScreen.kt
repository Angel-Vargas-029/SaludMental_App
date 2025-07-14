// ARCHIVO: app/src/main/java/com/example/saludmental/ui/screens/RegisterScreen.kt
// REEMPLAZAR TODO EL CONTENIDO EXISTENTE

package com.example.saludmental.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.saludmental.data.models.AuthState
import com.example.saludmental.ui.components.*
import com.example.saludmental.ui.theme.*
import com.example.saludmental.ui.viewmodel.AuthViewModel
import com.example.saludmental.util.ConnectivityObserver

@Composable
fun RegisterScreen(
    navController: NavController,
    authViewModel: AuthViewModel = viewModel()
) {
    val context = LocalContext.current
    val isConnected = ConnectivityObserver.isConnected(context)

    val authState by authViewModel.authState.collectAsStateWithLifecycle()
    val isUserSignedIn by authViewModel.isUserSignedIn.collectAsStateWithLifecycle()

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }

    var nameError by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    var birthDateError by remember { mutableStateOf("") }

    // Navegar a home si el usuario ya está autenticado
    LaunchedEffect(isUserSignedIn) {
        if (isUserSignedIn) {
            navController.navigate("home") {
                popUpTo("register") { inclusive = true }
            }
        }
    }

    // Manejar estados de autenticación
    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Success -> {
                navController.navigate("home") {
                    popUpTo("register") { inclusive = true }
                }
                authViewModel.clearAuthState()
            }
            is AuthState.Error -> {
                // El error se muestra en la UI
            }
            else -> { /* No hacer nada */ }
        }
    }

    // ✅ DISEÑO MODERNO CON SCROLL Y BARRAS DEL SISTEMA
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.background,
                        MaterialTheme.colorScheme.surface
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Spacer para la status bar
            Spacer(
                modifier = Modifier.height(
                    WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
                )
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(24.dp))

                // ✨ HEADER CON BRANDING
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        MindfulLavenderLight.copy(alpha = 0.1f),
                                        CalmBlueLight.copy(alpha = 0.1f),
                                        SerenityGreenLight.copy(alpha = 0.1f)
                                    )
                                )
                            )
                            .padding(28.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                Icons.Default.PersonAdd,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(56.dp)
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = "Únete a nosotros",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Comienza tu viaje hacia el bienestar emocional",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // ✨ FORMULARIO EN CARD ELEGANTE
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp)
                    ) {
                        Text(
                            text = "Crear cuenta",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(bottom = 20.dp)
                        )

                        // ALERTAS DE ESTADO
                        if (!isConnected) {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text(
                                    text = "⚠️ Sin conexión a Internet",
                                    modifier = Modifier.padding(16.dp),
                                    color = MaterialTheme.colorScheme.onErrorContainer,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        if (authState is AuthState.Error) {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text(
                                    text = (authState as AuthState.Error).message,
                                    modifier = Modifier.padding(16.dp),
                                    color = MaterialTheme.colorScheme.onErrorContainer,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        // CAMPOS DE ENTRADA
                        InputField(
                            label = "Nombre completo",
                            value = name,
                            onValueChange = {
                                name = it
                                nameError = ""
                            },
                            placeholder = "Ej. Juan Pérez"
                        )
                        if (nameError.isNotEmpty()) {
                            Text(
                                text = nameError,
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(start = 4.dp, top = 4.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        InputField(
                            label = "Correo electrónico",
                            value = email,
                            onValueChange = {
                                email = it
                                emailError = ""
                            },
                            keyboardType = KeyboardType.Email,
                            placeholder = "ejemplo@correo.com"
                        )
                        if (emailError.isNotEmpty()) {
                            Text(
                                text = emailError,
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(start = 4.dp, top = 4.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        InputField(
                            label = "Contraseña",
                            value = password,
                            onValueChange = {
                                password = it
                                passwordError = ""
                            },
                            isPassword = true,
                            placeholder = "Mínimo 6 caracteres"
                        )
                        if (passwordError.isNotEmpty()) {
                            Text(
                                text = passwordError,
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(start = 4.dp, top = 4.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        InputField(
                            label = "Fecha de nacimiento",
                            value = birthDate,
                            onValueChange = {
                                birthDate = it
                                birthDateError = ""
                            },
                            placeholder = "dd/mm/aaaa (opcional)"
                        )
                        if (birthDateError.isNotEmpty()) {
                            Text(
                                text = birthDateError,
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.bodySmall,
                                modifier = Modifier.padding(start = 4.dp, top = 4.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // BOTÓN PRINCIPAL
                        Button(
                            onClick = {
                                if (!isConnected) return@Button

                                var isValid = true

                                if (name.isBlank()) {
                                    nameError = "El nombre no puede estar vacío"
                                    isValid = false
                                }

                                if (email.isBlank()) {
                                    emailError = "El correo no puede estar vacío"
                                    isValid = false
                                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                                    emailError = "Formato de correo inválido"
                                    isValid = false
                                }

                                if (password.isBlank()) {
                                    passwordError = "La contraseña no puede estar vacía"
                                    isValid = false
                                } else if (password.length < 6) {
                                    passwordError = "Mínimo 6 caracteres"
                                    isValid = false
                                }

                                if (isValid) {
                                    authViewModel.signUp(email, password, name)
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            enabled = authState !is AuthState.Loading && isConnected,
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            if (authState is AuthState.Loading) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(20.dp),
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                            }
                            Text(
                                "Crear cuenta",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // BOTÓN SECUNDARIO
                        OutlinedButton(
                            onClick = { navController.popBackStack() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text(
                                "Ya tengo cuenta",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
            }

            // Spacer para la navigation bar
            Spacer(
                modifier = Modifier.height(
                    WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding() + 16.dp
                )
            )
        }
    }
}