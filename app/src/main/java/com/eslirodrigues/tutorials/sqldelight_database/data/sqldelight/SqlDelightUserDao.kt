package com.eslirodrigues.tutorials.sqldelight_database.data.sqldelight

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.eslirodrigues.tutorials.TutorialSqlDelightDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import tutorialsqldelightdb.SqldelightUserEntity
import javax.inject.Inject

class SqlDelightUserDao @Inject constructor(
    sqlDelightUserDatabase: TutorialSqlDelightDatabase
) {
    private val queries = sqlDelightUserDatabase.sqldelightUserEntityQueries

    suspend fun addUser(userId: Long?, userName: String) = withContext(Dispatchers.IO) {
        queries.addUser(id = userId, userName)
    }

    suspend fun updateUser(user: SqldelightUserEntity) = withContext(Dispatchers.IO) {
        queries.updateUser(user.id, user.userName)
    }

    suspend fun deleteUser(user: SqldelightUserEntity) = withContext(Dispatchers.IO) {
        queries.deleteUser(user.id)
    }

    fun getAllUsers(): Flow<List<SqldelightUserEntity>> =
        queries.getAllUsers().asFlow().mapToList(Dispatchers.IO)
}