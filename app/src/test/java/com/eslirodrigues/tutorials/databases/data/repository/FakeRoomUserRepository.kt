package com.eslirodrigues.tutorials.databases.data.repository

import com.eslirodrigues.tutorials.databases.data.DatabasesUserResult
import com.eslirodrigues.tutorials.databases.data.model.DatabasesUser
import com.eslirodrigues.tutorials.databases.room.RoomUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class FakeRoomUserRepository : DatabasesUserRepository {
    private val userList = mutableListOf<RoomUser>()

    override suspend fun addUser(user: DatabasesUser): Flow<DatabasesUserResult<String>> = flow {
        userList.add(RoomUser(user.id, user.userName))
        emit(DatabasesUserResult.Success("User Added"))
    }

    override suspend fun updateUser(user: DatabasesUser): Flow<DatabasesUserResult<String>> = flow {
        val findUserWithId = userList.find { it.id == user.id }
        userList.remove(findUserWithId)
        userList.add(RoomUser(user.id, user.userName))
        emit(DatabasesUserResult.Success("User Updated"))
    }

    override suspend fun deleteUser(user: DatabasesUser): Flow<DatabasesUserResult<String>> = flow {
        userList.remove(RoomUser(user.id, user.userName))
        emit(DatabasesUserResult.Success("User Deleted"))
    }

    override suspend fun getAllUsers(): Flow<DatabasesUserResult<List<DatabasesUser>>> = flow {
        val databasesUserList = userList.map { roomUser ->
            DatabasesUser(roomUser.id, roomUser.userName)
        }
        emit(DatabasesUserResult.Success(databasesUserList))
    }
}