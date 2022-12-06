package com.eslirodrigues.tutorials.retrofit_api.data.api

import com.eslirodrigues.tutorials.retrofit_api.data.model.dto.RetrofitApiProductPost
import com.eslirodrigues.tutorials.retrofit_api.extension.readFile
import com.google.common.truth.Truth
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
class RetrofitApiProductServiceTest {
    // 	https://onboarding-project5973.p.rapidapi.com/catalog/product - POST
    // 	https://onboarding-project5973.p.rapidapi.com/catalog/product/708643 - GET
    // 	https://onboarding-project5973.p.rapidapi.com/catalog/product/708643 - DELETE

    private lateinit var retrofitProductApi: RetrofitApiProductService
    private lateinit var mockWebServer: MockWebServer
    private val createProductFile = "retrofit_api/createProductBodyFile.json".readFile()
    private val deleteProductFile = "retrofit_api/deleteProductFile.json".readFile()
    private val getProductFile = "retrofit_api/getProductFile.json".readFile()

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()

        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        retrofitProductApi = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(RetrofitApiProductService::class.java)
    }

    @After
    fun close() {
        mockWebServer.shutdown()
    }

    @Test
    fun createProduct_Assert_IsPathRight() = runTest {
        mockWebServer.enqueue(MockResponse().setBody(createProductFile))

        retrofitProductApi.createProduct(RetrofitApiProductPost()).toRetrofitApiProduct()

        val request = runCatching {
            mockWebServer.takeRequest(timeout = 5, unit = TimeUnit.SECONDS)
        }.getOrNull()

        Truth.assertThat(request?.path).isEqualTo("/catalog/product")
    }

    @Test
    fun createProduct_Assert_IsNameRight() = runTest {
        mockWebServer.enqueue(MockResponse().setBody(getProductFile).setResponseCode(200)) // 400

        val product = retrofitProductApi.createProduct(
            RetrofitApiProductPost()
        ).toRetrofitApiProduct()

        Truth.assertThat(product.name).isEqualTo("er")
    }

    @Test
    fun deleteProduct_Assert_IsProductDeleted() = runTest {
        mockWebServer.enqueue(MockResponse().setBody(deleteProductFile))

        val response = retrofitProductApi.deleteProductById("714786")

        Truth.assertThat(response.isSuccessful).isTrue()
    }
}