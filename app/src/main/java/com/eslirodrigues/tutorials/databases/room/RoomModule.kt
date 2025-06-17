package com.eslirodrigues.tutorials.databases.room

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabasesModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ): RoomUserDatabase = Room.databaseBuilder(
        context,
        RoomUserDatabase::class.java,
        "room_user_database"
    ).fallbackToDestructiveMigration(false).build()

    @Singleton
    @Provides
    fun provideRoomUserDao(
        roomDatabase: RoomUserDatabase
    ) : RoomUserDao = roomDatabase.roomUserDao()

    @Singleton
    @Provides
    fun provideRoomRepository(
        roomUserDao: RoomUserDao
    ) : RoomUserRepositoryImpl = RoomUserRepositoryImpl(roomUserDao)
}