package com.example.saludmental.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.saludmental.ui.components.*
import com.example.saludmental.util.ConnectivityObserver

@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    val isConnected = ConnectivityObserver.isConnected(context)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TopBar("Ingreso")

        if (!isConnected) {
            Text(
                text = "Sin conexión a Internet",
                color = Color.Red,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

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
            Text(emailError, color = Color.Red, fontSize = MaterialTheme.typography.bodySmall.fontSize)
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
            Text(passwordError, color = Color.Red, fontSize = MaterialTheme.typography.bodySmall.fontSize)
        }

        Spacer(modifier = Modifier.height(16.dp))

        PrimaryButton(
            text = "Iniciar Sesión",
            onClick = {
                if (!isConnected) return@PrimaryButton

                var isValid = true

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
                    navController.navigate("home") // será sustituido por login real en FASE 3.2
                }
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        PrimaryButton(
            text = "Ir a Registro",
            onClick = { navController.navigate("register") }
        )
    }
}
