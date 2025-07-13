package com.example.saludmental.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.saludmental.data.MockData
import com.example.saludmental.ui.components.PostCard

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
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Column(
                    modifier = Modifier.padding(vertical = 16.dp)
                ) {
                    Text(
                        text = "Salud Mental App",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Tu bienestar es nuestra prioridad",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            item {
                Text(
                    text = "Tips y Recursos",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            items(MockData.psychologyPosts) { post ->
                PostCard(post = post)
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
