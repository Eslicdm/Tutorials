package com.eslirodrigues.tutorials.firebase_firestore.di

import com.eslirodrigues.tutorials.firebase_firestore.data.firebase.FirestoreDB
import com.eslirodrigues.tutorials.firebase_firestore.data.repository.FirestoreDBRepository
import com.eslirodrigues.tutorials.firebase_firestore.data.repository.FirestoreDBRepositoryImpl
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirestoreDBModule {

    @Singleton
    @Provides
    fun provideFirestoreDBReference(): CollectionReference = Firebase.firestore.collection("user")

    @Singleton
    @Provides
    fun provideFirestoreDBRepository(
        firestoreDB: FirestoreDB
    ) : FirestoreDBRepository = FirestoreDBRepositoryImpl(firestoreDB)
}