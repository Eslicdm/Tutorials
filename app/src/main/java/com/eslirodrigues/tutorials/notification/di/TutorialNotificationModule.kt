package com.eslirodrigues.tutorials.notification.di

import android.content.Context
import androidx.core.app.NotificationManagerCompat
import com.eslirodrigues.tutorials.notification.notification.TutorialNotification
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TutorialNotificationModule {

    @Singleton
    @Provides
    fun provideTutorialNotification(
        @ApplicationContext context: Context,
        notificationManagerCompat: NotificationManagerCompat
    ): TutorialNotification = TutorialNotification(context, notificationManagerCompat)

    // use Compat to older and new devices - NotificationCompat, NotificationManagerCompat
    @Singleton
    @Provides
    fun provideNotificationManagerCompat(
        @ApplicationContext context: Context
    ): NotificationManagerCompat = NotificationManagerCompat.from(context)
}