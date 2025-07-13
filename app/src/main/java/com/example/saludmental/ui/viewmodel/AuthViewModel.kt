package com.example.saludmental.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saludmental.data.models.AuthState
import com.example.saludmental.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val authRepository = AuthRepository()

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    private val _isUserSignedIn = MutableStateFlow(authRepository.isUserSignedIn())
    val isUserSignedIn: StateFlow<Boolean> = _isUserSignedIn.asStateFlow()

    init {
        checkAuthState()
    }

    private fun checkAuthState() {
        _isUserSignedIn.value = authRepository.isUserSignedIn()
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading

            authRepository.signIn(email, password)
                .onSuccess {
                    _authState.value = AuthState.Success
                    _isUserSignedIn.value = true
                }
                .onFailure { exception ->
                    _authState.value = AuthState.Error(
                        when {
                            exception.message?.contains("password") == true -> "Contraseña incorrecta"
                            exception.message?.contains("user-not-found") == true -> "Usuario no encontrado"
                            exception.message?.contains("invalid-email") == true -> "Email inválido"
                            exception.message?.contains("network") == true -> "Error de conexión"
                            else -> "Error: ${exception.message}"
                        }
                    )
                }
        }
    }

    fun signUp(email: String, password: String, name: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading

            authRepository.signUp(email, password, name)
                .onSuccess {
                    _authState.value = AuthState.Success
                    _isUserSignedIn.value = true
                }
                .onFailure { exception ->
                    _authState.value = AuthState.Error(
                        when {
                            exception.message?.contains("email-already-in-use") == true -> "Este email ya está registrado"
                            exception.message?.contains("weak-password") == true -> "La contraseña es muy débil"
                            exception.message?.contains("invalid-email") == true -> "Email inválido"
                            exception.message?.contains("network") == true -> "Error de conexión"
                            else -> "Error: ${exception.message}"
                        }
                    )
                }
        }
    }

    fun signOut() {
        authRepository.signOut()
        _isUserSignedIn.value = false
        _authState.value = AuthState.Idle
    }

    fun getUserDisplayName(): String {
        return authRepository.getUserDisplayName()
    }

    fun getUserEmail(): String {
        return authRepository.getUserEmail()
    }

    fun clearAuthState() {
        _authState.value = AuthState.Idle
    }
}
