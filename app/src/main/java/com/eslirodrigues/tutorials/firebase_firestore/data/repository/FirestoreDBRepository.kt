package com.eslirodrigues.tutorials.firebase_firestore.data.repository

import com.eslirodrigues.tutorials.firebase_firestore.data.FirestoreDBResult
import com.eslirodrigues.tutorials.firebase_firestore.data.model.FirestoreDBUser
import kotlinx.coroutines.flow.Flow

interface FirestoreDBRepository {
    fun getUsers(): Flow<FirestoreDBResult<List<FirestoreDBUser?>>>
    suspend fun createUser(user: FirestoreDBUser): Flow<FirestoreDBResult<String>>
    suspend fun updateUser(user: FirestoreDBUser): Flow<FirestoreDBResult<String>>
    suspend fun deleteUser(user: FirestoreDBUser): Flow<FirestoreDBResult<String>>
}