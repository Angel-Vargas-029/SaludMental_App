// ARCHIVO: app/src/main/java/com/example/saludmental/ui/screens/AppointmentsScreen.kt
// REEMPLAZAR TODO EL CONTENIDO EXISTENTE

package com.example.saludmental.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.saludmental.data.MockData
import com.example.saludmental.data.models.Appointment
import com.example.saludmental.ui.components.AppointmentCard
import com.example.saludmental.ui.components.ConsistentTopBar

@Composable
fun AppointmentsScreen(navController: NavController) {
    var appointments by remember { mutableStateOf(MockData.appointments) }
    var completedAppointments by remember { mutableStateOf<List<Appointment>>(emptyList()) }

    // Filtrar solo las citas no completadas
    val pendingAppointments = appointments.filter { !it.isCompleted }

    // ✅ DISEÑO CONSISTENTE CON BARRAS DEL SISTEMA
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            // ✅ BARRA SUPERIOR CONSISTENTE
            ConsistentTopBar(
                title = "Mis Citas",
                navController = navController,
                showBackButton = true
            )

            // CONTENIDO PRINCIPAL
            if (pendingAppointments.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(24.dp)
                    ) {
                        Text(
                            text = "📅 No tienes citas pendientes",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Todas tus citas han sido completadas",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item { Spacer(modifier = Modifier.height(8.dp)) }

                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.7f)
                            ),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(20.dp)
                            ) {
                                Text(
                                    text = "📋 Próximas Citas",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Marca como completada una vez que hayas asistido",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }

                    items(pendingAppointments) { appointment ->
                        AppointmentCard(
                            appointment = appointment,
                            onMarkCompleted = {
                                // Marcar como completada y mover al historial
                                val updatedAppointment = appointment.copy(isCompleted = true)
                                appointments = appointments.map {
                                    if (it.id == appointment.id) updatedAppointment else it
                                }
                                completedAppointments = completedAppointments + updatedAppointment
                            },
                            showCompleteButton = true
                        )
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
}