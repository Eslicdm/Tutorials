package com.eslirodrigues.tutorials.databases.data.repository

import com.eslirodrigues.tutorials.databases.data.DatabasesUserResult
import com.eslirodrigues.tutorials.databases.data.model.DatabasesUser
import com.eslirodrigues.tutorials.databases.room.RoomUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DatabasesUserRepositoryImpl @Inject constructor(
    roomUserRepository: RoomUserRepository,
) : DatabasesUserRepository {
    private val currentRepository = roomUserRepository

    override suspend fun addUser(user: DatabasesUser):
            Flow<DatabasesUserResult<String>> = currentRepository.addUser(user)

    override suspend fun updateUser(user: DatabasesUser):
            Flow<DatabasesUserResult<String>> = currentRepository.updateUser(user)

    override suspend fun deleteUser(user: DatabasesUser):
            Flow<DatabasesUserResult<String>> = currentRepository.deleteUser(user)

    override suspend fun getAllUsers():
            Flow<DatabasesUserResult<List<DatabasesUser>>> = currentRepository.getAllUsers()
}