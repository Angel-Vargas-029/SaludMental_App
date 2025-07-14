// ARCHIVO: app/src/main/java/com/example/saludmental/ui/screens/DiaryScreen.kt
// REEMPLAZAR TODO EL CONTENIDO EXISTENTE

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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.saludmental.data.models.DiaryEntry
import com.example.saludmental.ui.components.ConsistentTopBar
import com.example.saludmental.ui.components.DiaryCard
import com.example.saludmental.ui.components.InputField
import com.example.saludmental.ui.components.PrimaryButton
import com.example.saludmental.ui.viewmodel.DiaryViewModel

@Composable
fun DiaryScreen(
    navController: NavController,
    diaryViewModel: DiaryViewModel = viewModel()
) {
    val uiState by diaryViewModel.uiState.collectAsStateWithLifecycle()
    val operationState by diaryViewModel.operationState.collectAsStateWithLifecycle()

    var showDialog by remember { mutableStateOf(false) }
    var editingEntry by remember { mutableStateOf<DiaryEntry?>(null) }
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    // Mostrar mensajes de operaciones
    LaunchedEffect(operationState) {
        operationState?.let {
            // Aquí podrías mostrar un Snackbar, por simplicidad lo limpiamos
            diaryViewModel.clearOperationState()
        }
    }

    // ✨ USAR SCAFFOLD SIN PADDING PARA MANEJO MANUAL DE BARRAS DEL SISTEMA
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            // ✅ BARRA SUPERIOR CONSISTENTE
            ConsistentTopBar(
                title = "Mi Diario Personal",
                navController = navController,
                showBackButton = true
            )

            // CONTENIDO PRINCIPAL
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                when {
                    uiState.isLoading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                CircularProgressIndicator(
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    "Cargando entradas...",
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }

                    uiState.errorMessage != null -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(24.dp)
                            ) {
                                Text(
                                    text = "❌ Error:",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.error
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = uiState.errorMessage!!,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }

                    uiState.entries.isEmpty() -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(24.dp)
                            ) {
                                Text(
                                    text = "📝 No hay entradas aún",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Toca el botón + para crear tu primera entrada",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }

                    else -> {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            item { Spacer(modifier = Modifier.height(8.dp)) }

                            items(uiState.entries) { entry ->
                                DiaryCard(
                                    entry = entry,
                                    onEdit = {
                                        editingEntry = entry
                                        title = entry.title
                                        content = entry.content
                                        showDialog = true
                                    },
                                    onDelete = {
                                        diaryViewModel.deleteDiaryEntry(entry.id)
                                    }
                                )
                            }

                            // ✅ PADDING INFERIOR PARA LA NAVEGACIÓN DEL SISTEMA
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

        // ✅ FAB CON PADDING APROPIADO PARA BARRAS DEL SISTEMA
        FloatingActionButton(
            onClick = {
                editingEntry = null
                title = ""
                content = ""
                showDialog = true
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .padding(
                    bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
                ),
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ) {
            Icon(Icons.Default.Add, contentDescription = "Agregar entrada")
        }
    }

    // Dialog para agregar/editar entrada
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(
                    if (editingEntry == null) "Nueva Entrada" else "Editar Entrada",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            },
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
                                diaryViewModel.saveDiaryEntry(title, content)
                            } else {
                                diaryViewModel.updateDiaryEntry(editingEntry!!.id, title, content)
                            }
                            showDialog = false
                            title = ""
                            content = ""
                        }
                    }
                )
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancelar", color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            },
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.primary,
            textContentColor = MaterialTheme.colorScheme.onSurface
        )
    }
}