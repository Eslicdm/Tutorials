package com.eslirodrigues.tutorials.databases.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: RoomUser)

    @Update
    suspend fun updateUser(user: RoomUser)

    @Delete
    suspend fun deleteUser(user: RoomUser)

    @Query("SELECT * FROM room_user_table")
    fun getAllUsers(): Flow<List<RoomUser>>
}