package com.eslirodrigues.tutorials.sqldelight_database.di

import android.content.Context
import com.eslirodrigues.tutorials.TutorialSqlDelightDatabase
import com.eslirodrigues.tutorials.sqldelight_database.data.repository.SqlDelightUserRepository
import com.eslirodrigues.tutorials.sqldelight_database.data.repository.SqlDelightUserRepositoryImpl
import com.eslirodrigues.tutorials.sqldelight_database.data.sqldelight.SqlDelightUserDao
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SqlDelightModule {

    @Singleton
    @Provides
    fun provideSqlDriverDatabase(
        @ApplicationContext context: Context,
    ): SqlDriver = AndroidSqliteDriver(
        schema = TutorialSqlDelightDatabase.Schema,
        context = context,
        name = "sql_delight_db"
    )

    @Singleton
    @Provides
    fun provideSqlDelightUserDao(
        sqlDriverDatabase: SqlDriver,
    ): SqlDelightUserDao = SqlDelightUserDao(TutorialSqlDelightDatabase(sqlDriverDatabase))

    @Singleton
    @Provides
    fun provideSqlDelightRepository(
        sqlDelightUserDao: SqlDelightUserDao,
    ): SqlDelightUserRepository = SqlDelightUserRepositoryImpl(sqlDelightUserDao)
}