package com.example.saludmental.data.repository

import com.example.saludmental.data.models.DiaryEntry
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.util.*

class DiaryRepository {
    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    // Obtener el ID del usuario actual
    private fun getCurrentUserId(): String {
        return auth.currentUser?.uid ?: throw IllegalStateException("Usuario no autenticado")
    }

    // Obtener todas las entradas del usuario en tiempo real
    fun getUserDiaryEntries(): Flow<List<DiaryEntry>> = callbackFlow {
        val userId = getCurrentUserId()

        val listener = firestore
            .collection("users")
            .document(userId)
            .collection("diary_entries")
            .orderBy("date", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                val entries = snapshot?.documents?.mapNotNull { doc ->
                    doc.toObject(DiaryEntry::class.java)?.copy(id = doc.id)
                } ?: emptyList()

                trySend(entries)
            }

        awaitClose { listener.remove() }
    }

    // Guardar nueva entrada
    suspend fun saveDiaryEntry(entry: DiaryEntry): Result<String> {
        return try {
            val userId = getCurrentUserId()
            val entryData = hashMapOf(
                "title" to entry.title,
                "content" to entry.content,
                "date" to entry.date,
                "preview" to entry.preview
            )

            val documentRef = firestore
                .collection("users")
                .document(userId)
                .collection("diary_entries")
                .add(entryData)
                .await()

            Result.success(documentRef.id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Actualizar entrada existente
    suspend fun updateDiaryEntry(entry: DiaryEntry): Result<Unit> {
        return try {
            val userId = getCurrentUserId()
            val entryData = hashMapOf(
                "title" to entry.title,
                "content" to entry.content,
                "date" to entry.date,
                "preview" to entry.preview
            )

            firestore
                .collection("users")
                .document(userId)
                .collection("diary_entries")
                .document(entry.id)
                .set(entryData)
                .await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Eliminar entrada
    suspend fun deleteDiaryEntry(entryId: String): Result<Unit> {
        return try {
            val userId = getCurrentUserId()

            firestore
                .collection("users")
                .document(userId)
                .collection("diary_entries")
                .document(entryId)
                .delete()
                .await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Crear perfil básico del usuario (opcional)
    suspend fun createUserProfile(name: String, email: String): Result<Unit> {
        return try {
            val userId = getCurrentUserId()
            val userProfile = hashMapOf(
                "name" to name,
                "email" to email,
                "createdAt" to Date()
            )

            firestore
                .collection("users")
                .document(userId)
                .set(userProfile)
                .await()

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
