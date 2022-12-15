package com.eslirodrigues.tutorials.room_database.data.repository

import com.eslirodrigues.tutorials.room_database.data.RoomResult
import com.eslirodrigues.tutorials.room_database.data.model.RoomUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class FakeRoomUserRepository : RoomUserRepository {
    private val userList = mutableListOf<RoomUser>()

    override suspend fun addUser(user: RoomUser): Flow<RoomResult<String>> = flow {
        userList.add(user)
        emit(RoomResult.Success("User Added"))
    }

    override suspend fun updateUser(user: RoomUser): Flow<RoomResult<String>> = flow {
        val findUserWithId = userList.find { it.id == user.id }
        userList.remove(findUserWithId)
        userList.add(user)
        emit(RoomResult.Success("User Updated"))
    }

    override suspend fun deleteUser(user: RoomUser): Flow<RoomResult<String>> = flow {
        userList.remove(user)
        emit(RoomResult.Success("User Deleted"))
    }

    override suspend fun getAllUsers(): Flow<RoomResult<List<RoomUser>>> = flow {
        emit(RoomResult.Success(userList))
    }
}