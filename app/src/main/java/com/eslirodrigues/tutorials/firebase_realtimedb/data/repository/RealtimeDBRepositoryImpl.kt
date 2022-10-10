package com.eslirodrigues.tutorials.firebase_realtimedb.data.repository

import com.eslirodrigues.tutorials.firebase_realtimedb.data.firebase.RealtimeDB
import com.eslirodrigues.tutorials.firebase_realtimedb.data.model.RealtimeDBUser
import com.eslirodrigues.tutorials.firebase_realtimedb.data.util.RealtimeDBResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RealtimeDBRepositoryImpl @Inject constructor(
    private val firebase: RealtimeDB
) : RealtimeDBRepository {
    override suspend fun createUser(user: RealtimeDBUser): Flow<RealtimeDBResult<String>> {
        return firebase.createUser(user)
    }

    override suspend fun getUser(): Flow<RealtimeDBResult<List<RealtimeDBUser?>>> {
        return firebase.getUsers()
    }

    override suspend fun updateUser(user: RealtimeDBUser): Flow<RealtimeDBResult<String>> {
        return firebase.updateUser(user)
    }

    override suspend fun deleteUser(user: RealtimeDBUser): Flow<RealtimeDBResult<String>> {
        return firebase.deleteUser(user)
    }
}