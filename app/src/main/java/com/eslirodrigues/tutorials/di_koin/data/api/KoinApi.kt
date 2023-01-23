package com.eslirodrigues.tutorials.di_koin.data.api

import android.content.Context
import com.eslirodrigues.tutorials.R

class KoinApi (
    private val context: Context
) {
    fun getString(): String {
        return context.getString(R.string.dependency_string)
    }
}