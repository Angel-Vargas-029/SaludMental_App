// ARCHIVO: app/src/main/java/com/example/saludmental/ui/screens/HomeScreen.kt
// REEMPLAZAR TODO EL CONTENIDO EXISTENTE

package com.example.saludmental.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SelfImprovement
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.saludmental.data.MockData
import com.example.saludmental.ui.components.PostCard
import com.example.saludmental.ui.theme.*
import com.example.saludmental.ui.viewmodel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    authViewModel: AuthViewModel = viewModel()
) {
    var showUserMenu by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.SelfImprovement,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "Salud Mental",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                actions = {
                    Box {
                        IconButton(onClick = { showUserMenu = true }) {
                            Icon(Icons.Default.Person, contentDescription = "Perfil")
                        }

                        DropdownMenu(
                            expanded = showUserMenu,
                            onDismissRequest = { showUserMenu = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("👤 ${authViewModel.getUserDisplayName()}") },
                                onClick = { }
                            )
                            DropdownMenuItem(
                                text = { Text("📧 ${authViewModel.getUserEmail()}") },
                                onClick = { }
                            )
                            HorizontalDivider()
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        "🚪 Cerrar sesión",
                                        color = MaterialTheme.colorScheme.error
                                    )
                                },
                                onClick = {
                                    authViewModel.signOut()
                                    navController.navigate("login") {
                                        popUpTo(0) { inclusive = true }
                                    }
                                    showUserMenu = false
                                }
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f)
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp
            ) {
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("diary") },
                    icon = {
                        Icon(
                            Icons.Default.Edit,
                            contentDescription = "Diario",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    label = {
                        Text(
                            "Diario",
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("music") },
                    icon = {
                        Icon(
                            Icons.Default.MusicNote,
                            contentDescription = "Música",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    label = {
                        Text(
                            "Música",
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("appointments") },
                    icon = {
                        Icon(
                            Icons.Default.Event,
                            contentDescription = "Citas",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    label = {
                        Text(
                            "Citas",
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("history") },
                    icon = {
                        Icon(
                            Icons.Default.History,
                            contentDescription = "Historial",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    },
                    label = {
                        Text(
                            "Historial",
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { Spacer(modifier = Modifier.height(8.dp)) }

            // ✨ TARJETA DE BIENVENIDA CON GRADIENTE
            item {
                WelcomeCard(authViewModel.getUserDisplayName())
            }

            // 🎯 ACCESOS RÁPIDOS
            item {
                QuickAccessSection(navController)
            }

            // 📚 TÍTULO DE CONTENIDO EDUCATIVO
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Icon(
                        Icons.Default.Psychology,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Recursos de Bienestar",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            // 📄 POSTS EDUCATIVOS
            items(MockData.psychologyPosts) { post ->
                PostCard(post = post)
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
fun WelcomeCard(userName: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            SerenityGreenLight.copy(alpha = 0.1f),
                            CalmBlueLight.copy(alpha = 0.1f),
                            MindfulLavenderLight.copy(alpha = 0.1f)
                        )
                    )
                )
                .padding(24.dp)
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Favorite,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "¡Hola, $userName!",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Tu bienestar emocional es nuestra prioridad.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    lineHeight = 24.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "¿Cómo te sientes hoy? Explora nuestras herramientas para mantener tu equilibrio mental.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    lineHeight = 20.sp
                )
            }
        }
    }
}

@Composable
fun QuickAccessSection(navController: NavController) {
    Column {
        Text(
            text = "Herramientas Rápidas",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            QuickAccessCard(
                title = "Mi Diario",
                subtitle = "Expresa tus emociones",
                icon = Icons.Default.Edit,
                color = SerenityGreen,
                modifier = Modifier.weight(1f)
            ) {
                navController.navigate("diary")
            }

            QuickAccessCard(
                title = "Relajación",
                subtitle = "Música calmante",
                icon = Icons.Default.MusicNote,
                color = CalmBlue,
                modifier = Modifier.weight(1f)
            ) {
                navController.navigate("music")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            QuickAccessCard(
                title = "Citas",
                subtitle = "Agenda tu sesión",
                icon = Icons.Default.LocalHospital,
                color = MindfulLavender,
                modifier = Modifier.weight(1f)
            ) {
                navController.navigate("appointments")
            }

            QuickAccessCard(
                title = "Historial",
                subtitle = "Tu progreso",
                icon = Icons.Default.History,
                color = SerenityGreenDark,
                modifier = Modifier.weight(1f)
            ) {
                navController.navigate("history")
            }
        }
    }
}

@Composable
fun QuickAccessCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    color: androidx.compose.ui.graphics.Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = modifier.height(100.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = color.copy(alpha = 0.1f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                icon,
                contentDescription = title,
                tint = color,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = color,
                textAlign = TextAlign.Center
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }
}