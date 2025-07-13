package com.example.saludmental.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saludmental.data.models.DiaryEntry
import com.example.saludmental.data.repository.DiaryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.util.*

data class DiaryUiState(
    val entries: List<DiaryEntry> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class DiaryViewModel : ViewModel() {
    private val repository = DiaryRepository()

    private val _uiState = MutableStateFlow(DiaryUiState())
    val uiState: StateFlow<DiaryUiState> = _uiState.asStateFlow()

    private val _operationState = MutableStateFlow<String?>(null)
    val operationState: StateFlow<String?> = _operationState.asStateFlow()

    init {
        loadDiaryEntries()
    }

    private fun loadDiaryEntries() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            repository.getUserDiaryEntries()
                .catch { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = "Error al cargar entradas: ${exception.message}"
                    )
                }
                .collect { entries ->
                    _uiState.value = _uiState.value.copy(
                        entries = entries,
                        isLoading = false,
                        errorMessage = null
                    )
                }
        }
    }

    fun saveDiaryEntry(title: String, content: String) {
        if (title.isBlank() || content.isBlank()) return

        viewModelScope.launch {
            val entry = DiaryEntry(
                title = title,
                content = content,
                date = Date()
            )

            repository.saveDiaryEntry(entry)
                .onSuccess {
                    _operationState.value = "Entrada guardada exitosamente"
                }
                .onFailure { exception ->
                    _operationState.value = "Error al guardar: ${exception.message}"
                }
        }
    }

    fun updateDiaryEntry(entryId: String, title: String, content: String) {
        if (title.isBlank() || content.isBlank()) return

        viewModelScope.launch {
            val entry = DiaryEntry(
                id = entryId,
                title = title,
                content = content,
                date = Date()
            )

            repository.updateDiaryEntry(entry)
                .onSuccess {
                    _operationState.value = "Entrada actualizada exitosamente"
                }
                .onFailure { exception ->
                    _operationState.value = "Error al actualizar: ${exception.message}"
                }
        }
    }

    fun deleteDiaryEntry(entryId: String) {
        viewModelScope.launch {
            repository.deleteDiaryEntry(entryId)
                .onSuccess {
                    _operationState.value = "Entrada eliminada exitosamente"
                }
                .onFailure { exception ->
                    _operationState.value = "Error al eliminar: ${exception.message}"
                }
        }
    }

    fun clearOperationState() {
        _operationState.value = null
    }
}