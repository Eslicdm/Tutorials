package com.eslirodrigues.tutorials.firebase_realtimedb.data.firebase

import com.eslirodrigues.tutorials.firebase_realtimedb.data.model.RealtimeDBUser
import com.eslirodrigues.tutorials.firebase_realtimedb.data.util.RealtimeDBResult
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class RealtimeDB @Inject constructor(
    private val userReference: DatabaseReference
) {
    suspend fun getUsers() : Flow<RealtimeDBResult<List<RealtimeDBUser?>>> = callbackFlow {
        trySend(RealtimeDBResult.Loading)
        userReference.keepSynced(true)
        val event = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val users = snapshot.children.map { dataSnapshot ->
                    dataSnapshot.getValue<RealtimeDBUser>()
                }
                trySend(RealtimeDBResult.Success(users))
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(RealtimeDBResult.Error(Throwable(error.message)))
            }
        }
        userReference.addValueEventListener(event)
        awaitClose { close() }
    }

    fun createUser(realtimeDB: RealtimeDBUser) : Flow<RealtimeDBResult<String>> = callbackFlow {
        val userId = userReference.push().key!!
        val user = RealtimeDBUser(id = userId, name = realtimeDB.name)
        userReference.child(userId).setValue(user)
            .addOnSuccessListener { trySend(RealtimeDBResult.Success("User Added")) }
            .addOnFailureListener { trySend(RealtimeDBResult.Error(Throwable(it.message))) }
        awaitClose { close() }
    }

    suspend fun updateUser(realtimeDB: RealtimeDBUser) : Flow<RealtimeDBResult<String>> = callbackFlow {
        userReference.child(realtimeDB.id!!).updateChildren(realtimeDB.toMap())
            .addOnSuccessListener { trySend(RealtimeDBResult.Success("User updated")) }
            .addOnFailureListener { trySend(RealtimeDBResult.Error(Throwable(it.message))) }
        awaitClose { close() }
    }

    suspend fun deleteUser(realtimeDB: RealtimeDBUser) : Flow<RealtimeDBResult<String>> = callbackFlow {
        userReference.child(realtimeDB.id!!).removeValue()
            .addOnSuccessListener { trySend(RealtimeDBResult.Success("User deleted")) }
            .addOnFailureListener { trySend(RealtimeDBResult.Error(Throwable(it.message))) }
        awaitClose { close() }
    }
}