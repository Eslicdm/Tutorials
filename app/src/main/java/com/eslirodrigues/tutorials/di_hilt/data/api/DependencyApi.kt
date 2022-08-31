package com.eslirodrigues.tutorials.di_hilt.data.api

import android.content.Context
import com.eslirodrigues.tutorials.R
import javax.inject.Inject

class DependencyApi @Inject constructor(
    private val context: Context
) {
    fun getString(): String {
        return context.getString(R.string.dependency_string)
    }
}