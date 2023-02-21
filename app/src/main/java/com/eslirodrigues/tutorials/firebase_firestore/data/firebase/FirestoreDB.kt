package com.eslirodrigues.tutorials.firebase_firestore.data.firebase

import com.eslirodrigues.tutorials.firebase_firestore.data.FirestoreDBResult
import com.eslirodrigues.tutorials.firebase_firestore.data.model.FirestoreDBUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FirestoreDB @Inject constructor(
    private val reference: CollectionReference,
) {
    fun getUsers(): Flow<FirestoreDBResult<List<FirestoreDBUser>>> = callbackFlow {
        trySend(FirestoreDBResult.Loading)
        val listener = reference.addSnapshotListener { value, error ->
            if (error != null) trySend(FirestoreDBResult.Error(Throwable(error.message)))
            if (value != null) {
                val users = value.map { documentSnapshot ->
                    documentSnapshot.toObject<FirestoreDBUser>()
                }
                trySend(FirestoreDBResult.Success(users))
            }
        }
        awaitClose { listener.remove() }
    }

    suspend fun createUser(
        firestoreDBUser: FirestoreDBUser
    ): Flow<FirestoreDBResult<String>> = callbackFlow {
        val documentID = reference.document().id
        reference.document(documentID).set(firestoreDBUser.copy(id = documentID))
            .addOnSuccessListener { trySend(FirestoreDBResult.Success("User Added")) }
            .addOnFailureListener { trySend(FirestoreDBResult.Error(Throwable(it.message))) }
        awaitClose { close() }
    }

    suspend fun updateUser(
        firestoreDBUser: FirestoreDBUser
    ): Flow<FirestoreDBResult<String>> = callbackFlow {
        if (firestoreDBUser.id != null) {
            reference.document(firestoreDBUser.id).update(firestoreDBUser.toMap())
                .addOnSuccessListener { trySend(FirestoreDBResult.Success("User Updated")) }
                .addOnFailureListener { trySend(FirestoreDBResult.Error(Throwable(it.message))) }
        }
        awaitClose { close() }
    }

    suspend fun deleteUser(
        firestoreDBUser: FirestoreDBUser
    ): Flow<FirestoreDBResult<String>> = callbackFlow {
        if (firestoreDBUser.id != null) {
            reference.document(firestoreDBUser.id).delete()
                .addOnSuccessListener { trySend(FirestoreDBResult.Success("User Deleted")) }
                .addOnFailureListener { trySend(FirestoreDBResult.Error(Throwable(it.message))) }
        }
        awaitClose { close() }
    }
}