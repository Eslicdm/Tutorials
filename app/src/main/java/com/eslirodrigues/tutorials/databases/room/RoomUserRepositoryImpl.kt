package com.eslirodrigues.tutorials.databases.room

import com.eslirodrigues.tutorials.databases.data.DatabasesUserResult
import com.eslirodrigues.tutorials.databases.data.model.DatabasesUser
import com.eslirodrigues.tutorials.databases.data.repository.DatabasesUserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class RoomUserRepositoryImpl @Inject constructor(
    private val roomUserDao: RoomUserDao,
) : DatabasesUserRepository {

    override suspend fun addUser(user: DatabasesUser): Flow<DatabasesUserResult<String>> = flow {
        emit(DatabasesUserResult.Loading)
        try {
            roomUserDao.addUser(RoomUser(user.id, user.userName))
            emit(DatabasesUserResult.Success("User Added"))
        } catch (e: Exception) {
            emit(DatabasesUserResult.Error(Throwable(e.message)))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun updateUser(user: DatabasesUser): Flow<DatabasesUserResult<String>> = flow {
        emit(DatabasesUserResult.Loading)
        try {
            roomUserDao.updateUser(RoomUser(user.id, user.userName))
            emit(DatabasesUserResult.Success("User Updated"))
        } catch (e: Exception) {
            emit(DatabasesUserResult.Error(Throwable(e.message)))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun deleteUser(user: DatabasesUser): Flow<DatabasesUserResult<String>> = flow {
        emit(DatabasesUserResult.Loading)
        try {
            roomUserDao.deleteUser(RoomUser(user.id, user.userName))
            emit(DatabasesUserResult.Success("User Deleted"))
        } catch (e: Exception) {
            emit(DatabasesUserResult.Error(Throwable(e.message)))
        }
    }.flowOn(Dispatchers.IO)

    override fun getAllUsers(): Flow<DatabasesUserResult<List<DatabasesUser>>> = callbackFlow {
        roomUserDao.getAllUsers().onStart {
            trySend(DatabasesUserResult.Loading)
        }.catch { e ->
            trySend(DatabasesUserResult.Error(Throwable(e.message)))
        }.collectLatest { list ->
            val databasesUserList = list.map { roomUser ->
                DatabasesUser(roomUser.id, roomUser.userName)
            }
            trySend(DatabasesUserResult.Success(databasesUserList))
        }
        awaitClose { close() }
    }.flowOn(Dispatchers.IO)
}