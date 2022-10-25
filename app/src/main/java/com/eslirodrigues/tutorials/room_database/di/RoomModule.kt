package com.eslirodrigues.tutorials.room_database.di

import android.content.Context
import androidx.room.Room
import com.eslirodrigues.tutorials.room_database.data.repository.RoomUserRepository
import com.eslirodrigues.tutorials.room_database.data.repository.RoomUserRepositoryImpl
import com.eslirodrigues.tutorials.room_database.data.room.RoomUserDao
import com.eslirodrigues.tutorials.room_database.data.room.RoomUserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ): RoomUserDatabase = Room.databaseBuilder(
        context,
        RoomUserDatabase::class.java,
        "room_user_database"
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideRoomUserDao(
        roomDatabase: RoomUserDatabase
    ) : RoomUserDao = roomDatabase.roomUserDao()

    @Singleton
    @Provides
    fun provideRoomRepository(
        roomUserDao: RoomUserDao
    ) : RoomUserRepository = RoomUserRepositoryImpl(roomUserDao)
}