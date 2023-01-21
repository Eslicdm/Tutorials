package com.eslirodrigues.tutorials.notification.ui.navigation

sealed class NotificationNavRoute(val route: String) {
    object NotificationScreen : NotificationNavRoute("nav_notification_screen")
    object NotificationResponseScreen : NotificationNavRoute(
        "nav_notification_response_screen/{response}"
    )
}