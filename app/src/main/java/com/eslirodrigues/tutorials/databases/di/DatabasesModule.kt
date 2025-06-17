package com.eslirodrigues.tutorials.databases.di

import com.eslirodrigues.tutorials.databases.data.repository.DatabasesUserRepository
import com.eslirodrigues.tutorials.databases.data.repository.DatabasesUserRepositoryImpl
import com.eslirodrigues.tutorials.databases.room.RoomUserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabasesModule {

    @Singleton
    @Provides
    fun provideDatabasesUserRepository(
        roomUserRepositoryImpl: RoomUserRepositoryImpl
    ) : DatabasesUserRepository = DatabasesUserRepositoryImpl(roomUserRepositoryImpl)
}