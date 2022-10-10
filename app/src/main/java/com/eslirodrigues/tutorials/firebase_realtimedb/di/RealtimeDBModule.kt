package com.eslirodrigues.tutorials.firebase_realtimedb.di

import com.eslirodrigues.tutorials.firebase_realtimedb.data.firebase.RealtimeDB
import com.eslirodrigues.tutorials.firebase_realtimedb.data.repository.RealtimeDBRepository
import com.eslirodrigues.tutorials.firebase_realtimedb.data.repository.RealtimeDBRepositoryImpl
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RealtimeDBModule {

    @Singleton
    @Provides
    fun provideRealtimeDBReference(): DatabaseReference = Firebase.database.getReference("user")

    @Singleton
    @Provides
    fun provideRealtimeDBClass(
        userReference: DatabaseReference
    ) : RealtimeDB = RealtimeDB(userReference = userReference)

    @Singleton
    @Provides
    fun provideRepository(
        firebase: RealtimeDB
    ) : RealtimeDBRepository = RealtimeDBRepositoryImpl(firebase)
}