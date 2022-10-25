package com.eslirodrigues.tutorials.room_database.data.repository

import com.eslirodrigues.tutorials.room_database.data.RoomResult
import com.eslirodrigues.tutorials.room_database.data.model.RoomUser
import kotlinx.coroutines.flow.Flow

interface RoomUserRepository {
    suspend fun addUser(user: RoomUser) : Flow<RoomResult<String>>
    suspend fun updateUser(user: RoomUser) : Flow<RoomResult<String>>
    suspend fun deleteUser(user: RoomUser) : Flow<RoomResult<String>>
    suspend fun getAllUsers(): Flow<RoomResult<List<RoomUser>>>
}