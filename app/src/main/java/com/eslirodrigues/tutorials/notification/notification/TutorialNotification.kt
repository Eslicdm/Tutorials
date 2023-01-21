package com.eslirodrigues.tutorials.notification.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.Person
import androidx.core.app.RemoteInput
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.net.toUri
import com.eslirodrigues.tutorials.MainActivity
import com.eslirodrigues.tutorials.R
import com.eslirodrigues.tutorials.notification.ui.navigation.TutorialNotificationConstants.NOTIFICATION_RESPONSE
import com.eslirodrigues.tutorials.notification.ui.navigation.TutorialNotificationConstants.NOTIFICATION_RESPONSE_CHANNEL_ID
import com.eslirodrigues.tutorials.notification.ui.navigation.TutorialNotificationConstants.NOTIFICATION_RESULT_KEY
import com.eslirodrigues.tutorials.notification.ui.navigation.TutorialNotificationConstants.NOTIFICATION_URI
import javax.inject.Inject

class TutorialNotification @Inject constructor(
    private val context: Context,
    private val notificationManagerCompat: NotificationManagerCompat
) {

    @RequiresApi(Build.VERSION_CODES.O)
    fun openNotificationSettings() {
        // ACTION_CHANNEL_NOTIFICATION_SETTINGS
        val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
            putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
//            putExtra(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS, "Tap Action ID")
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(context, intent, null)
    }

    // importance - HIGH (sound and pop on screen), DEFAULT (sound), LOW (no sound), MIN (no sound or visual interruption)
    private fun createNotificationChannel(
        channelName: String,
        channelDesc: String,
        channelId: String,
        importance: Int = NotificationManager.IMPORTANCE_DEFAULT,
        showBadge: Boolean = true
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = channelDesc
                setShowBadge(showBadge)
            }
            notificationManagerCompat.createNotificationChannel(channel)
//            notificationManagerCompat.createNotificationChannelGroup(NotificationChannelGroup(groupId, groupName))
        }
    }

    // use NotificationChannel.setImportance() instead of NotificationCompat.Builder.setPriority() for Android 8.0 and higher.
    fun showNotificationWithTextAndTitle(
        context: Context = this.context,
        title: String,
        content: String,
        priority: Int = NotificationCompat.PRIORITY_DEFAULT,
    ) {
        val channelId = "Text and Title ID"
        val notificationId = 1

        createNotificationChannel(
            channelName = "Text and Title",
            channelDesc = "Notification with Text and Title",
            channelId = channelId,
        )

        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_notifications)
            .setContentTitle(title)
            .setContentText(content)
            .setStyle(
//                NotificationCompat.BigTextStyle().bigText(
//                    "Big text style used in text and title notification to expand notification"
//                )
                NotificationCompat.InboxStyle()
                    .addLine("First line text")
                    .addLine("Second line text")
                    .addLine("Third line text")
            )
            .setPriority(priority)

        notificationManagerCompat.notify(notificationId, builder.build())
    }

    // Flags - Immutable (another apps can't change the PendingIntent), Update_Current (only your app can change), Mutable (another apps can change)
    fun showNotificationWithTapActionThatOpensNewScreenWithArgs(
        context: Context = this.context,
        title: String,
        content: String,
    ) {
        val channelId = "Tap Action ID"
        val notificationId = 2
        val requestCode = 2
        val flag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) PendingIntent.FLAG_IMMUTABLE else 0

        createNotificationChannel(
            channelName = "Tap Action",
            channelDesc = "Notification with Tap Action",
            channelId = channelId,
        )

//        val intent = Intent(context, MainActivity::class.java).apply {
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        }

        val intent = Intent(
            Intent.ACTION_VIEW,
            "$NOTIFICATION_URI/$NOTIFICATION_RESPONSE=Response from notification".toUri(),
            context,
            MainActivity::class.java
        )
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, requestCode, intent, flag)

        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_notifications)
            .setContentTitle(title)
            .setContentText(content)
            .setContentIntent(pendingIntent)
            .setLargeIcon(
                ResourcesCompat.getDrawable(context.resources, R.drawable.ic_image, context.theme)?.toBitmap()
            )
            .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
            .setPublicVersion(
                NotificationCompat.Builder(context, channelId)
                    .setSmallIcon(R.drawable.ic_notifications)
                    .setContentTitle("public title")
                    .setContentText("public content")
                    .build()
            )
            .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            builder.setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(Icon.createWithResource(context, R.drawable.ic_image))
                    .bigLargeIcon(null)
            )
        }

        notificationManagerCompat.notify(notificationId, builder.build())
    }

    fun showNotificationWithResponseAction(
        context: Context = this.context,
        title: String,
        content: String,
    ) {
        val channelId = NOTIFICATION_RESPONSE_CHANNEL_ID
        val notificationId = 3
        val requestCode = 3
        val flag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) PendingIntent.FLAG_MUTABLE else 0

        val replyTextField = RemoteInput.Builder(NOTIFICATION_RESULT_KEY).setLabel("message").build()
        val replyIntent = Intent(context, TutorialNotificationBroadcastReceiver::class.java)
        val replyPendingIntent = PendingIntent.getBroadcast(context, requestCode, replyIntent, flag)
        val replyAction = NotificationCompat.Action.Builder(
            0, "Reply", replyPendingIntent
        ).addRemoteInput(replyTextField).build()

        val person = Person.Builder().setName("Carl").build()

        createNotificationChannel(
            channelName = "Response Action",
            channelDesc = "Notification with Response Action",
            channelId = channelId,
        )

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, requestCode, intent, flag)

        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_notifications)
            .setContentTitle(title)
            .setContentText(content)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setContentIntent(pendingIntent)
            .setStyle(
                NotificationCompat.MessagingStyle(person).addMessage(
                    "message from carl", System.currentTimeMillis(), person
                )
            )
            .addAction(replyAction)

        notificationManagerCompat.notify(notificationId, builder.build())
    }

    // to download a file use DownloadManager
    fun showNotificationWithProgressBar(
        context: Context = this.context,
        title: String,
        content: String,
        progress: Int,
        maxProgress: Int,
    ) {
        val channelId = "Progress Bar ID"
        val notificationId = 4

        createNotificationChannel(
            channelName = "Progress Bar",
            channelDesc = "Notification with Progress Bar",
            channelId = channelId,
            importance = NotificationManager.IMPORTANCE_LOW
        )

        val builder = if (progress != maxProgress) {
            NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setProgress(maxProgress, progress, false)
                .setOngoing(true)
        } else {
            NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentTitle("Download Completed")
                .setProgress(0, 0, false)
        }

        notificationManagerCompat.notify(notificationId, builder.build())
    }

    fun showNotificationWithGroup (
        context: Context = this.context,
    ) {
        val channelId = "Full-Screen ID"
        val firstNotificationId = 5
        val secondNotificationId = 6
        val summaryGroupId = 7

        val groupName = "Text and Tile Group"

        createNotificationChannel(
            channelName = "Tap Action",
            channelDesc = "Notification with Tap Action",
            channelId = channelId,
//            showBadge = false
        )

        val firstGroupNotification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_notifications)
            .setContentTitle("First Group Title")
            .setContentText("First Group Text")
            .setGroup(groupName)

        val secondGroupNotification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_notifications)
            .setContentTitle("Second Group Title")
            .setContentText("Second Group Content")
            .setNumber(4)
            .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
            .setGroup(groupName)

        val summaryNotification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_notifications)
            .setContentTitle("Summary Title") // Only for API lower than 14
            .setContentText("Summary Content")
            .setStyle(NotificationCompat.InboxStyle().setSummaryText("John@gmail.com"))
            .setGroup(groupName) // Android 7, more than 4 messages will be grouped automatically
            .setGroupSummary(true)

        notificationManagerCompat.apply {
            notify(firstNotificationId, firstGroupNotification.build())
            notify(secondNotificationId, secondGroupNotification.build())
            notify(summaryGroupId, summaryNotification.build())
        }
    }

    fun cancelNotifications(notificationIDs: List<Int>?, shouldCancelAll: Boolean) {
        if (shouldCancelAll)
            notificationManagerCompat.cancelAll()
        else notificationIDs?.forEach { id ->
            notificationManagerCompat.cancel(id)
        }
        // notificationManagerCompat.deleteNotificationChannel(channelId)
        // notificationManagerCompat.deleteNotificationChannelGroup(groupId)
    }
}