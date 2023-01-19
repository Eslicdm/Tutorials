package com.eslirodrigues.tutorials.countdowntimer.core

import java.util.concurrent.TimeUnit

object TimeFormatExt {
    private const val FORMAT = "%02d:%02d:%02d"

    fun Long.timeFormat(): String = String.format(
        FORMAT,
        TimeUnit.MILLISECONDS.toHours(this),
        TimeUnit.MILLISECONDS.toMinutes(this) % 60,
        TimeUnit.MILLISECONDS.toSeconds(this) % 60
    )
}