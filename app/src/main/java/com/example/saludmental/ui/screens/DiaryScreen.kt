package com.example.saludmental.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.saludmental.data.models.DiaryEntry
import com.example.saludmental.ui.components.BackButton
import com.example.saludmental.ui.components.DiaryCard
import com.example.saludmental.ui.components.InputField
import com.example.saludmental.ui.components.PrimaryButton
import java.util.*

@Composable
fun DiaryScreen(navController: NavController) {
    var diaryEntries by remember { mutableStateOf(getSampleEntries()) }
    var showDialog by remember { mutableStateOf(false) }
    var editingEntry by remember { mutableStateOf<DiaryEntry?>(null) }
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

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
                    text = "Mi Diario Personal",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(48.dp))
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    editingEntry = null
                    title = ""
                    content = ""
                    showDialog = true
                }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar entrada")
            }
        }
    ) { innerPadding ->
        if (diaryEntries.isEmpty()) {
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
                        text = "Aún no hay entradas",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "Toca el botón + para crear tu primera entrada",
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
                item { Spacer(modifier = Modifier.height(8.dp)) }

                items(diaryEntries) { entry ->
                    DiaryCard(
                        entry = entry,
                        onEdit = {
                            editingEntry = entry
                            title = entry.title
                            content = entry.content
                            showDialog = true
                        },
                        onDelete = {
                            diaryEntries = diaryEntries.filter { it.id != entry.id }
                        }
                    )
                }

                item { Spacer(modifier = Modifier.height(100.dp)) }
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(if (editingEntry == null) "Nueva Entrada" else "Editar Entrada") },
            text = {
                Column {
                    InputField(
                        label = "Título",
                        value = title,
                        onValueChange = { title = it },
                        placeholder = "¿Cómo te sientes hoy?"
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    InputField(
                        label = "Contenido",
                        value = content,
                        onValueChange = { content = it },
                        placeholder = "Describe tus pensamientos y emociones..."
                    )
                }
            },
            confirmButton = {
                PrimaryButton(
                    text = if (editingEntry == null) "Guardar" else "Actualizar",
                    onClick = {
                        if (title.isNotBlank() && content.isNotBlank()) {
                            if (editingEntry == null) {
                                val newEntry = DiaryEntry(
                                    id = UUID.randomUUID().toString(),
                                    title = title,
                                    content = content
                                )
                                diaryEntries = diaryEntries + newEntry
                            } else {
                                diaryEntries = diaryEntries.map {
                                    if (it.id == editingEntry!!.id) {
                                        it.copy(title = title, content = content)
                                    } else it
                                }
                            }
                            showDialog = false
                        }
                    }
                )
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}

private fun getSampleEntries(): List<DiaryEntry> {
    return listOf(
        DiaryEntry(
            id = "1",
            title = "Un día productivo",
            content = "Hoy me levanté temprano y pude completar todas mis tareas. Me siento muy satisfecho con lo que logré. La organización realmente marca la diferencia en mi estado de ánimo."
        ),
        DiaryEntry(
            id = "2",
            title = "Reflexiones sobre el estrés",
            content = "Últimamente he notado que el estrés del trabajo me está afectando más de lo normal. Necesito encontrar mejores formas de manejarlo. Tal vez debería probar la meditación."
        )
    )
}
