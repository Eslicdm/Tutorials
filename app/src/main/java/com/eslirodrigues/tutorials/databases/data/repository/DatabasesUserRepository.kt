package com.eslirodrigues.tutorials.databases.data.repository

import com.eslirodrigues.tutorials.databases.data.DatabasesUserResult
import com.eslirodrigues.tutorials.databases.data.model.DatabasesUser
import kotlinx.coroutines.flow.Flow

interface DatabasesUserRepository {
    suspend fun addUser(user: DatabasesUser) : Flow<DatabasesUserResult<String>>
    suspend fun updateUser(user: DatabasesUser) : Flow<DatabasesUserResult<String>>
    suspend fun deleteUser(user: DatabasesUser) : Flow<DatabasesUserResult<String>>
    suspend fun getAllUsers(): Flow<DatabasesUserResult<List<DatabasesUser>>>
}