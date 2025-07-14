// ARCHIVO: app/src/main/java/com/example/saludmental/ui/screens/MusicScreen.kt
// REEMPLAZAR TODO EL CONTENIDO EXISTENTE

package com.example.saludmental.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.saludmental.data.MockData
import com.example.saludmental.ui.components.ConsistentTopBar
import com.example.saludmental.ui.components.MusicCard
import com.example.saludmental.ui.theme.*

@Composable
fun MusicScreen(navController: NavController) {
    // ✅ DISEÑO CONSISTENTE CON BARRAS DEL SISTEMA
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            // ✅ BARRA SUPERIOR CONSISTENTE
            ConsistentTopBar(
                title = "Música Relajante",
                navController = navController,
                showBackButton = true
            )

            // CONTENIDO PRINCIPAL
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item { Spacer(modifier = Modifier.height(8.dp)) }

                item {
                    // ✨ TARJETA DE INTRODUCCIÓN CON GRADIENTE MUSICAL
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(20.dp)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(20.dp))
                        ) {
                            // Fondo con gradiente sutil
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(
                                        brush = Brush.horizontalGradient(
                                            colors = listOf(
                                                CalmBlue.copy(alpha = 0.1f),
                                                MindfulLavender.copy(alpha = 0.1f),
                                                SerenityGreen.copy(alpha = 0.1f)
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
                                            Icons.Default.MusicNote,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.primary,
                                            modifier = Modifier.size(32.dp)
                                        )
                                        Spacer(modifier = Modifier.width(12.dp))
                                        Text(
                                            text = "🎵 Música para tu bienestar",
                                            style = MaterialTheme.typography.titleLarge,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.primary
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(12.dp))

                                    Text(
                                        text = "Canciones cuidadosamente seleccionadas para reducir el estrés y promover la relajación profunda.",
                                        style = MaterialTheme.typography.bodyLarge,
                                        color = MaterialTheme.colorScheme.onSurface,
                                        lineHeight = MaterialTheme.typography.bodyLarge.lineHeight
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(
                                        text = "Usa auriculares para una experiencia inmersiva",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }
                        }
                    }
                }

                items(MockData.musicTracks) { track ->
                    MusicCard(track = track)
                }

                // ✅ PADDING INFERIOR PARA BARRAS DEL SISTEMA
                item {
                    Spacer(
                        modifier = Modifier.height(
                            WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding() + 16.dp
                        )
                    )
                }
            }
        }
    }
}