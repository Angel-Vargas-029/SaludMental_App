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
fun RegisterScreen(navController: NavController) {
    val context = LocalContext.current
    val isConnected = ConnectivityObserver.isConnected(context)

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }

    var nameError by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    var birthDateError by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TopBar("Bienvenido")

        if (!isConnected) {
            Text(
                text = "Sin conexión a Internet",
                color = Color.Red,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        InputField(label = "Nombre", value = name, onValueChange = { name = it }, placeholder = "Ej. Angel")
        if (nameError.isNotEmpty()) {
            Text(nameError, color = Color.Red, fontSize = MaterialTheme.typography.bodySmall.fontSize)
        }

        Spacer(modifier = Modifier.height(8.dp))

        InputField(
            label = "Correo electrónico",
            value = email,
            onValueChange = { email = it },
            keyboardType = KeyboardType.Email,
            placeholder = "nombre@gmail.com"
        )
        if (emailError.isNotEmpty()) {
            Text(emailError, color = Color.Red, fontSize = MaterialTheme.typography.bodySmall.fontSize)
        }

        Spacer(modifier = Modifier.height(8.dp))

        InputField(
            label = "Contraseña",
            value = password,
            onValueChange = { password = it },
            isPassword = true,
            placeholder = "Mínimo 6 caracteres"
        )
        if (passwordError.isNotEmpty()) {
            Text(passwordError, color = Color.Red, fontSize = MaterialTheme.typography.bodySmall.fontSize)
        }

        Spacer(modifier = Modifier.height(8.dp))

        InputField(
            label = "Fecha de nacimiento",
            value = birthDate,
            onValueChange = { birthDate = it },
            keyboardType = KeyboardType.Number,
            placeholder = "dd-mm-yyyy"
        )
        if (birthDateError.isNotEmpty()) {
            Text(birthDateError, color = Color.Red, fontSize = MaterialTheme.typography.bodySmall.fontSize)
        }

        Spacer(modifier = Modifier.height(16.dp))

        PrimaryButton(
            text = "Registrarse",
            onClick = {
                if (!isConnected) return@PrimaryButton

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

                if (birthDate.isBlank()) {
                    birthDateError = "La fecha no puede estar vacía"
                    isValid = false
                }

                if (isValid) {
                    navController.navigate("home") // será sustituido por registro real
                }
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        PrimaryButton(
            text = "Volver al Login",
            onClick = { navController.popBackStack() }
        )
    }
}
