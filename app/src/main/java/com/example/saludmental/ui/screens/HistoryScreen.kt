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
import com.example.saludmental.ui.components.BackButton
import java.util.*

@Composable
fun HistoryScreen(navController: NavController) {
    // Crear algunas citas completadas de ejemplo
    var completedAppointments by remember {
        mutableStateOf(getSampleCompletedAppointments())
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BackButton(navController)
                Text(
                    text = "Historial de Citas",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(48.dp))
            }
        }
    ) { innerPadding ->
        if (completedAppointments.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "📝 No hay historial aún",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
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
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "✅ Sesiones Completadas",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "${completedAppointments.size} sesión${if (completedAppointments.size != 1) "es" else ""} completada${if (completedAppointments.size != 1) "s" else ""}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
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

                item {
                    Spacer(modifier = Modifier.height(16.dp))
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
        )
    )
}
