package com.eslirodrigues.tutorials.firebase_firestore.data.repository

import com.eslirodrigues.tutorials.firebase_firestore.data.FirestoreDBResult
import com.eslirodrigues.tutorials.firebase_firestore.data.firebase.FirestoreDB
import com.eslirodrigues.tutorials.firebase_firestore.data.model.FirestoreDBUser
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FirestoreDBRepositoryImpl @Inject constructor(
    private val firestoreDB: FirestoreDB,
) : FirestoreDBRepository {

    override fun getUsers(): Flow<FirestoreDBResult<List<FirestoreDBUser?>>> = firestoreDB.getUsers()

    override suspend fun createUser(
        user: FirestoreDBUser
    ): Flow<FirestoreDBResult<String>> = firestoreDB.createUser(user)

    override suspend fun updateUser(
        user: FirestoreDBUser
    ): Flow<FirestoreDBResult<String>> = firestoreDB.updateUser(user)

    override suspend fun deleteUser(
        user: FirestoreDBUser
    ): Flow<FirestoreDBResult<String>> = firestoreDB.deleteUser(user)
}