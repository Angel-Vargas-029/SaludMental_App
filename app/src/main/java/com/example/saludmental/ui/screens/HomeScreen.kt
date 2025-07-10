package com.example.saludmental.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.saludmental.ui.components.TopBar

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        bottomBar = {
            BottomAppBar {
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("diary") },
                    icon = { Icon(Icons.Default.Edit, contentDescription = "Diario") },
                    label = { Text("Diario") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("music") },
                    icon = { Icon(Icons.Default.MusicNote, contentDescription = "Música") },
                    label = { Text("Música") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("appointments") },
                    icon = { Icon(Icons.Default.Event, contentDescription = "Citas") },
                    label = { Text("Citas") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("history") },
                    icon = { Icon(Icons.Default.History, contentDescription = "Historial") },
                    label = { Text("Historial") }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(24.dp)
        ) {
            TopBar("Salud Mental App")
            Text("Elige una opción para comenzar")
        }
    }
}
