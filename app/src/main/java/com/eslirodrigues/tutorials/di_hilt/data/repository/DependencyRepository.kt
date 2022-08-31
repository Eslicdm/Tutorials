package com.eslirodrigues.tutorials.di_hilt.data.repository

import com.eslirodrigues.tutorials.di_hilt.data.api.DependencyApi
import javax.inject.Inject

class DependencyRepository @Inject constructor(
    private val dependencyApi: DependencyApi,
    private val stringOne: String,
    private val stringTwo: String
) {
    fun getString(): String = dependencyApi.getString()
    fun getStringOne(): String = stringOne
    fun getStringTwo(): String = stringTwo
}