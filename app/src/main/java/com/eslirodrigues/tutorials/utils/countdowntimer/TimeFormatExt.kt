package com.eslirodrigues.tutorials.utils.countdowntimer

import android.annotation.SuppressLint
import java.util.concurrent.TimeUnit

object TimeFormatExt {
    private const val FORMAT = "%02d:%02d:%02d"

    @SuppressLint("DefaultLocale")
    fun Long.timeFormat(): String = String.format(
        FORMAT,
        TimeUnit.MILLISECONDS.toHours(this),
        TimeUnit.MILLISECONDS.toMinutes(this) % 60,
        TimeUnit.MILLISECONDS.toSeconds(this) % 60
    )
}