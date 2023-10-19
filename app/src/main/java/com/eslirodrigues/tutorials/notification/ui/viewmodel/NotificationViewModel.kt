package com.eslirodrigues.tutorials.notification.ui.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eslirodrigues.tutorials.notification.notification.TutorialNotification
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val notification: TutorialNotification
) : ViewModel() {

    private val downloadProgress = mutableIntStateOf(0)

    fun showNotificationWithTextAndTitle(title: String, content: String) {
        notification.showNotificationWithTextAndTitle(title = title, content = content)
    }

    fun showNotificationWithTapAction(title: String, content: String) {
        notification.showNotificationWithTapActionThatOpensNewScreenWithArgs(title = title, content = content)
    }

    fun showNotificationWithResponseAction(title: String, content: String) {
        notification.showNotificationWithResponseAction(title = title, content = content)
    }

    fun showNotificationWithProgressBar() = viewModelScope.launch {
        while (downloadProgress.intValue < 100) {
            delay(500)
            downloadProgress.intValue += 10
            notification.showNotificationWithProgressBar(
                title = "Progress Bar Download",
                content = "${downloadProgress.intValue}/100",
                progress = downloadProgress.intValue,
                maxProgress = 100
            )
        }
    }

    fun showNotificationWithGroup() {
        notification.showNotificationWithGroup()
    }

    fun cancelNotifications(notificationIDs: List<Int>?, shouldCancelAll: Boolean) {
        notification.cancelNotifications(notificationIDs, shouldCancelAll)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun openNotificationSettings() {
        notification.openNotificationSettings()
    }
}