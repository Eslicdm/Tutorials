package com.eslirodrigues.tutorials.sqldelight_database.data.repository

import com.eslirodrigues.tutorials.sqldelight_database.data.SqlDelightResult
import kotlinx.coroutines.flow.Flow
import tutorialsqldelightdb.SqldelightUserEntity

interface SqlDelightUserRepository {
    fun addUser(userId: Long?, userName: String) : Flow<SqlDelightResult<String>>
    suspend fun updateUser(user: SqldelightUserEntity) : Flow<SqlDelightResult<String>>
    suspend fun deleteUser(user: SqldelightUserEntity) : Flow<SqlDelightResult<String>>
    suspend fun getAllUsers(): Flow<SqlDelightResult<List<SqldelightUserEntity>>>
}