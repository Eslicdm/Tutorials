package com.eslirodrigues.tutorials.room_database.data.room

import androidx.room.*
import com.eslirodrigues.tutorials.room_database.data.model.RoomUser
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