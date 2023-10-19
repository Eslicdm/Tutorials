package com.eslirodrigues.tutorials.notification.notification

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.app.RemoteInput
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.Person
import com.eslirodrigues.tutorials.R
import com.eslirodrigues.tutorials.notification.ui.navigation.TutorialNotificationConstants.NOTIFICATION_RESPONSE_CHANNEL_ID
import com.eslirodrigues.tutorials.notification.ui.navigation.TutorialNotificationConstants.NOTIFICATION_RESULT_KEY

class TutorialNotificationBroadcastReceiver : BroadcastReceiver() {

    // setConversationTitle() for group messages
    @SuppressLint("MissingPermission")
    override fun onReceive(context: Context?, intent: Intent?) {
        val requestCode = 3
        val notificationId = 3

        val resultTextField = RemoteInput.getResultsFromIntent(intent)
        if (resultTextField != null && context != null) {
            val inputText = resultTextField.getCharSequence(NOTIFICATION_RESULT_KEY).toString()
            val person = Person.Builder().setName("Me").build()
            val message = NotificationCompat.MessagingStyle.Message(
                inputText, System.currentTimeMillis(), person
            )
            val notificationStyle = NotificationCompat.MessagingStyle(person).addMessage(message)

            val replyTextField = androidx.core.app.RemoteInput.Builder(NOTIFICATION_RESULT_KEY)
                .setLabel("message")
                .build()
            val replyIntent = Intent(context, TutorialNotificationBroadcastReceiver::class.java)
            val replyPendingIntent = PendingIntent.getBroadcast(
                context, requestCode, replyIntent, PendingIntent.FLAG_MUTABLE
            )
            val replyAction = NotificationCompat.Action.Builder(
                0, "Reply", replyPendingIntent
            ).addRemoteInput(replyTextField).build()

            val replyNotification = NotificationCompat.Builder(context, NOTIFICATION_RESPONSE_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notifications)
                .setStyle(notificationStyle)
                .addAction(replyAction)

            NotificationManagerCompat.from(context).notify(notificationId, replyNotification.build())
        }
    }
}