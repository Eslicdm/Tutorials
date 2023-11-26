package com.eslirodrigues.tutorials.room_database.data.repository

import android.content.Context
import android.widget.Toast
import com.eslirodrigues.tutorials.room_database.data.RoomResult
import com.eslirodrigues.tutorials.room_database.data.model.RoomUser
import com.eslirodrigues.tutorials.room_database.data.room.RoomUserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomUserRepositoryImpl @Inject constructor(
    private val roomUserDao: RoomUserDao,
    private val context: Context
) : RoomUserRepository {
    override suspend fun addUser(user: RoomUser): Flow<RoomResult<String>> = flow {
        emit(RoomResult.Loading)
        try {
            roomUserDao.addUser(user)
            emit(RoomResult.Success("User Added"))
            withContext(Main) { Toast.makeText(context, "User Added", Toast.LENGTH_SHORT).show() }
        } catch (e: Exception) {
            emit(RoomResult.Error(Throwable(e.message)))
            withContext(Main) { Toast.makeText(context, "Added Error", Toast.LENGTH_SHORT).show() }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun updateUser(user: RoomUser): Flow<RoomResult<String>> = flow {
        emit(RoomResult.Loading)
        try {
            roomUserDao.updateUser(user)
            emit(RoomResult.Success("User Updated"))
            withContext(Main) { Toast.makeText(context, "User Updated", Toast.LENGTH_SHORT).show() }
        } catch (e: Exception) {
            emit(RoomResult.Error(Throwable(e.message)))
            withContext(Main) { Toast.makeText(context, "Updated Error", Toast.LENGTH_SHORT).show() }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun deleteUser(user: RoomUser): Flow<RoomResult<String>> = flow {
        emit(RoomResult.Loading)
        try {
            roomUserDao.deleteUser(user)
            emit(RoomResult.Success("User Deleted"))
            withContext(Main) { Toast.makeText(context, "User Deleted", Toast.LENGTH_SHORT).show() }
        } catch (e: Exception) {
            emit(RoomResult.Error(Throwable(e.message)))
            withContext(Main) { Toast.makeText(context, "Deleted Error", Toast.LENGTH_SHORT).show() }
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