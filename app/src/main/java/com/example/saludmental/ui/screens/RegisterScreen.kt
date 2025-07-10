package com.example.saludmental.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.saludmental.ui.components.*

@Composable
fun RegisterScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TopBar("Bienvenido")

        InputField(label = "Nombre", value = name, onValueChange = { name = it })

        Spacer(modifier = Modifier.height(8.dp))

        InputField(
            label = "Correo electrónico",
            value = email,
            onValueChange = { email = it },
            keyboardType = KeyboardType.Email
        )

        Spacer(modifier = Modifier.height(8.dp))

        InputField(
            label = "Contraseña",
            value = password,
            onValueChange = { password = it },
            isPassword = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        InputField(
            label = "Fecha de nacimiento",
            value = birthDate,
            onValueChange = { birthDate = it },
            keyboardType = KeyboardType.Number
        )

        Spacer(modifier = Modifier.height(16.dp))

        PrimaryButton(
            text = "Registrarse",
            onClick = {
                // En esta fase solo UI. Lógica se integrará en FASE 3.
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        PrimaryButton(
            text = "Volver al Login",
            onClick = {
                navController.popBackStack()
            }
        )
    }
}
