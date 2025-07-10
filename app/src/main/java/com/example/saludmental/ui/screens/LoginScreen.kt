package com.example.saludmental.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.saludmental.ui.components.*

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        TopBar("Ingreso")

        Spacer(modifier = Modifier.height(16.dp))

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

        Spacer(modifier = Modifier.height(16.dp))

        PrimaryButton(
            text = "Iniciar Sesión",
            onClick = { navController.navigate("home") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        PrimaryButton(
            text = "Ir a Registro",
            onClick = { navController.navigate("register") }
        )
    }
}
