package com.eslirodrigues.tutorials.retrofit_api.data.api

import com.eslirodrigues.tutorials.retrofit_api.extension.readFile
import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class RetrofitApiItemServiceTest {
    // url - https://tasty.p.rapidapi.com/tips/list?id=3562&from=0&size=2

    private lateinit var retrofitItemApi: RetrofitApiItemService
    private lateinit var mockWebServer: MockWebServer
    private val responseFile = "retrofit_api/getItemsApiResponseFile.json".readFile()

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()

        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        retrofitItemApi = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(RetrofitApiItemService::class.java)
    }

    @After
    fun close() {
        mockWebServer.shutdown()
    }

    @Test
    fun makeRequest_Assert_IsPathRight() = runTest {
        mockWebServer.enqueue(MockResponse().setBody(responseFile))

        retrofitItemApi.getItemsFromApi(category = "tips", from = "0", size = "2")
        val request = runCatching {
            mockWebServer.takeRequest(timeout = 5, unit = TimeUnit.SECONDS)
        }.getOrNull()

        assertThat(request?.path).isEqualTo("/tips/list?id=3562&from=0&size=2")
    }

    @Test
    fun getItems_Assert_AuthorNameIsNotEmpty() = runTest {
        mockWebServer.enqueue(MockResponse().setBody(responseFile))

        val items = retrofitItemApi.getItemsFromApi(category = "tips", from = "0", size = "2")

        assertThat(items.results.first().authorName).isNotEmpty()
    }
}