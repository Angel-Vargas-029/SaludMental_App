package com.example.saludmental.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.saludmental.data.models.AuthState
import com.example.saludmental.ui.components.*
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TopBar("Crear cuenta")

        if (!isConnected) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)
            ) {
                Text(
                    text = "⚠️ Sin conexión a Internet",
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.onErrorContainer
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // Mostrar errores de autenticación
        if (authState is AuthState.Error) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)
            ) {
                Text(
                    text = (authState as AuthState.Error).message,
                    modifier = Modifier.padding(16.dp),
                    color = MaterialTheme.colorScheme.onErrorContainer
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

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

        Spacer(modifier = Modifier.height(8.dp))

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

        Spacer(modifier = Modifier.height(8.dp))

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

        Spacer(modifier = Modifier.height(8.dp))

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
            modifier = Modifier.fillMaxWidth(),
            enabled = authState !is AuthState.Loading && isConnected
        ) {
            if (authState is AuthState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Text("Crear cuenta")
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ya tengo cuenta")
        }
    }
}
