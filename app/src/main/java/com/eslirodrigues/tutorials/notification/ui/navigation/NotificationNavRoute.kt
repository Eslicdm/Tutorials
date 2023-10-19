package com.eslirodrigues.tutorials.notification.ui.navigation

sealed class NotificationNavRoute(val route: String) {
    data object NotificationScreen : NotificationNavRoute("nav_notification_screen")
    data object NotificationResponseScreen : NotificationNavRoute(
        "nav_notification_response_screen/{response}"
    )
}