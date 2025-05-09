package com.eslirodrigues.tutorials.databases.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RoomUser::class], version = 1, exportSchema = false)
abstract class RoomUserDatabase : RoomDatabase() {
    abstract fun roomUserDao() : RoomUserDao
}