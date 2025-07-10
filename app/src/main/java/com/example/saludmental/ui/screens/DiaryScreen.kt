package com.example.saludmental.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.saludmental.ui.components.TopBar

@Composable
fun DiaryScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            TopBar("Diario Personal")
            Spacer(modifier = Modifier.height(8.dp))
            Text("Aquí podrás registrar tu estado emocional diario.")
        }
    }
}
