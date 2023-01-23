package com.eslirodrigues.tutorials.di_koin.data.repository

import com.eslirodrigues.tutorials.di_koin.data.api.KoinApi

class KoinRepository(
    private val koinApi: KoinApi,
    private val stringOne: String,
    private val stringTwo: String
) {
    fun getString(): String = koinApi.getString()
    fun getStringOne(): String = stringOne
    fun getStringTwo(): String = stringTwo
}