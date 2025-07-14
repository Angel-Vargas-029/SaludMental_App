// ARCHIVO: app/src/main/java/com/example/saludmental/ui/screens/HistoryScreen.kt
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
import com.example.saludmental.data.models.Appointment
import com.example.saludmental.ui.components.AppointmentCard
import com.example.saludmental.ui.components.ConsistentTopBar
import java.util.*

@Composable
fun HistoryScreen(navController: NavController) {
    // Crear algunas citas completadas de ejemplo
    var completedAppointments by remember {
        mutableStateOf(getSampleCompletedAppointments())
    }

    // ✅ DISEÑO CONSISTENTE CON BARRAS DEL SISTEMA
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            // ✅ BARRA SUPERIOR CONSISTENTE
            ConsistentTopBar(
                title = "Historial de Citas",
                navController = navController,
                showBackButton = true
            )

            // CONTENIDO PRINCIPAL
            if (completedAppointments.isEmpty()) {
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
                            text = "📝 No hay historial aún",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Las citas completadas aparecerán aquí",
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
                                containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.7f)
                            ),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(20.dp)
                            ) {
                                Text(
                                    text = "✅ Sesiones Completadas",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "${completedAppointments.size} sesión${if (completedAppointments.size != 1) "es" else ""} completada${if (completedAppointments.size != 1) "s" else ""}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }

                    items(completedAppointments) { appointment ->
                        AppointmentCard(
                            appointment = appointment,
                            onMarkCompleted = { /* No hacer nada, ya está completada */ },
                            onDelete = {
                                completedAppointments = completedAppointments.filter { it.id != appointment.id }
                            },
                            showCompleteButton = false
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

private fun getSampleCompletedAppointments(): List<Appointment> {
    return listOf(
        Appointment(
            id = "completed_1",
            title = "Consulta de seguimiento",
            psychologistName = "Dra. Laura Fernández",
            date = Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, -7) }.time,
            time = "2:00 PM",
            location = "Consultorio 302, Centro de Bienestar",
            isVirtual = false,
            isCompleted = true
        ),
        Appointment(
            id = "completed_2",
            title = "Terapia de relajación",
            psychologistName = "Dr. Miguel Torres",
            date = Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, -14) }.time,
            time = "4:30 PM",
            location = "Sesión Virtual",
            isVirtual = true,
            virtualLink = "https://meet.google.com/xyz-abcd-efg",
            isCompleted = true
        ),
        Appointment(
            id = "completed_3",
            title = "Sesión de mindfulness",
            psychologistName = "Lic. Carmen Vega",
            date = Calendar.getInstance().apply { add(Calendar.DAY_OF_MONTH, -21) }.time,
            time = "9:00 AM",
            location = "Consultorio 208, Centro de Bienestar",
            isVirtual = false,
            isCompleted = true
        )
    )
}