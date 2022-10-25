package com.eslirodrigues.tutorials.room_database.data.repository

import com.eslirodrigues.tutorials.room_database.data.RoomResult
import com.eslirodrigues.tutorials.room_database.data.model.RoomUser
import com.eslirodrigues.tutorials.room_database.data.room.RoomUserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class RoomUserRepositoryImpl @Inject constructor(
    private val roomUserDao: RoomUserDao
) : RoomUserRepository {
    override suspend fun addUser(user: RoomUser): Flow<RoomResult<String>> = flow {
        emit(RoomResult.Loading)
        try {
            roomUserDao.addUser(user)
            emit(RoomResult.Success("User Added"))
        } catch (e: Exception) {
            emit(RoomResult.Error(Throwable(e.message)))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun updateUser(user: RoomUser): Flow<RoomResult<String>> = flow {
        emit(RoomResult.Loading)
        try {
            roomUserDao.updateUser(user)
            emit(RoomResult.Success("User Updated"))
        } catch (e: Exception) {
            emit(RoomResult.Error(Throwable(e.message)))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun deleteUser(user: RoomUser): Flow<RoomResult<String>> = flow {
        emit(RoomResult.Loading)
        try {
            roomUserDao.deleteUser(user)
            emit(RoomResult.Success("User Deleted"))
        } catch (e: Exception) {
            emit(RoomResult.Error(Throwable(e.message)))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getAllUsers(): Flow<RoomResult<List<RoomUser>>> = callbackFlow {
        roomUserDao.getAllUsers().onStart {
            trySend(RoomResult.Loading)
        }.catch { e ->
            trySend(RoomResult.Error(Throwable(e.message)))
        }.collectLatest { list ->
            trySend(RoomResult.Success(list))
        }
        awaitClose { close() }
    }.flowOn(Dispatchers.IO)
}