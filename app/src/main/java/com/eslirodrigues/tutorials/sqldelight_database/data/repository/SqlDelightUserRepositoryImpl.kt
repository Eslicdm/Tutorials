package com.eslirodrigues.tutorials.sqldelight_database.data.repository

import com.eslirodrigues.tutorials.sqldelight_database.data.SqlDelightResult
import com.eslirodrigues.tutorials.sqldelight_database.data.sqldelight.SqlDelightUserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import tutorialsqldelightdb.SqldelightUserEntity
import javax.inject.Inject

class SqlDelightUserRepositoryImpl @Inject constructor(
    private val sqlDelightUserDao: SqlDelightUserDao
) : SqlDelightUserRepository {
    override fun addUser(userId: Long?, userName: String): Flow<SqlDelightResult<String>> = flow {
        emit(SqlDelightResult.Loading)
        try {
            sqlDelightUserDao.addUser(userId, userName)
            emit(SqlDelightResult.Success("User Added"))
        } catch (e: Exception) {
            emit(SqlDelightResult.Error(Throwable(e.message)))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun updateUser(user: SqldelightUserEntity): Flow<SqlDelightResult<String>> = flow {
        emit(SqlDelightResult.Loading)
        try {
            sqlDelightUserDao.updateUser(user)
            emit(SqlDelightResult.Success("User Updated"))
        } catch (e: Exception) {
            emit(SqlDelightResult.Error(Throwable(e.message)))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun deleteUser(user: SqldelightUserEntity): Flow<SqlDelightResult<String>> = flow {
        emit(SqlDelightResult.Loading)
        try {
            sqlDelightUserDao.deleteUser(user)
            emit(SqlDelightResult.Success("User Deleted"))
        } catch (e: Exception) {
            emit(SqlDelightResult.Error(Throwable(e.message)))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getAllUsers(): Flow<SqlDelightResult<List<SqldelightUserEntity>>> = callbackFlow {
        trySend(SqlDelightResult.Loading)
        sqlDelightUserDao.getAllUsers().catch { e ->
            trySend(SqlDelightResult.Error(Throwable(e.message)))
        }.collectLatest { users ->
            trySend(SqlDelightResult.Success(users))
        }
        awaitClose { close() }
    }.flowOn(Dispatchers.IO)
}