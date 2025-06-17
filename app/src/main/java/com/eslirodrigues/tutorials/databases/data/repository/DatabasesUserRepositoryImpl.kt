package com.eslirodrigues.tutorials.databases.data.repository

import com.eslirodrigues.tutorials.databases.data.DatabasesUserResult
import com.eslirodrigues.tutorials.databases.data.model.DatabasesUser
import com.eslirodrigues.tutorials.databases.room.RoomUserRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DatabasesUserRepositoryImpl @Inject constructor(
    roomUserRepositoryImpl: RoomUserRepositoryImpl,
) : DatabasesUserRepository {
    private val currentRepository = roomUserRepositoryImpl

    override suspend fun addUser(user: DatabasesUser):
            Flow<DatabasesUserResult<String>> = currentRepository.addUser(user)

    override suspend fun updateUser(user: DatabasesUser):
            Flow<DatabasesUserResult<String>> = currentRepository.updateUser(user)

    override suspend fun deleteUser(user: DatabasesUser):
            Flow<DatabasesUserResult<String>> = currentRepository.deleteUser(user)

    override fun getAllUsers():
            Flow<DatabasesUserResult<List<DatabasesUser>>> = currentRepository.getAllUsers()
}