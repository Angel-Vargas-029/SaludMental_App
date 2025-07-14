// ARCHIVO: app/src/main/java/com/example/saludmental/ui/components/ConsistentTopBar.kt
// CREAR NUEVO ARCHIVO

package com.example.saludmental.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

/**
 * Barra superior consistente para todas las pantallas
 * Maneja automáticamente el padding de la status bar
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConsistentTopBar(
    title: String,
    navController: NavController? = null,
    showBackButton: Boolean = true,
    trailingIcon: ImageVector? = null,
    onTrailingIconClick: (() -> Unit)? = null,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    // Padding para la status bar del sistema
    Column(modifier = modifier) {
        // Spacer para la status bar (altura estándar)
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(WindowInsets.statusBars.asPaddingValues().calculateTopPadding())
        )

        // Top bar real
        TopAppBar(
            title = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            },
            navigationIcon = {
                if (showBackButton && navController != null) {
                    IconButton(
                        onClick = { navController.navigate("home") }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver al inicio",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            },
            actions = {
                if (trailingIcon != null && onTrailingIconClick != null) {
                    IconButton(onClick = onTrailingIconClick) {
                        Icon(
                            imageVector = trailingIcon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f),
                titleContentColor = MaterialTheme.colorScheme.primary,
                actionIconContentColor = MaterialTheme.colorScheme.primary,
                navigationIconContentColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

/**
 * Versión simplificada para pantallas sin navegación
 */
@Composable
fun SimpleTopBar(
    title: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        // Spacer para la status bar
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(WindowInsets.statusBars.asPaddingValues().calculateTopPadding())
        )

        // Contenido de la barra
        Surface(
            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f),
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}