package com.eslirodrigues.tutorials.room_database.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.eslirodrigues.tutorials.room_database.data.model.RoomUser

@Database(entities = [RoomUser::class], version = 1, exportSchema = false)
abstract class RoomUserDatabase : RoomDatabase() {
    abstract fun roomUserDao() : RoomUserDao
}