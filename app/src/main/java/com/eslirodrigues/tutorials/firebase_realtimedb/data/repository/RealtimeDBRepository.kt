package com.eslirodrigues.tutorials.firebase_realtimedb.data.repository

import com.eslirodrigues.tutorials.firebase_realtimedb.data.model.RealtimeDBUser
import com.eslirodrigues.tutorials.firebase_realtimedb.data.util.RealtimeDBResult
import kotlinx.coroutines.flow.Flow

interface RealtimeDBRepository {
    suspend fun createUser(user: RealtimeDBUser): Flow<RealtimeDBResult<String>>
    suspend fun getUser(): Flow<RealtimeDBResult<List<RealtimeDBUser?>>>
    suspend fun updateUser(user: RealtimeDBUser): Flow<RealtimeDBResult<String>>
    suspend fun deleteUser(user: RealtimeDBUser): Flow<RealtimeDBResult<String>>
}