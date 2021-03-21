package com.test.app

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.test.app.data.AppRepository
import com.test.app.data.db.AppDao
import com.test.app.data.network.WeatherApi
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Rule
import org.junit.rules.TestRule
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

abstract class BaseTest {

    private val mockWebServer = MockWebServer()

    private val weatherApi = Retrofit.Builder()
        .baseUrl(mockWebServer.url("").toString())
        .addConverterFactory(MoshiConverterFactory.create())
        .build().create(WeatherApi::class.java)


    //DB testing will be done in "androidTest" pkg as that needs Context.
    //We can get ApplicationProvider or InstrumentationRegistry only in "androidTest" pkg.
    // Returning null from here for satisfying repository param.
    private val dao: AppDao? = null
    val appRepository = AppRepository(weatherApi,dao)

    var rule: TestRule = InstantTaskExecutorRule()

    fun setUp() {
    }

    fun setResponse(fileName: String) {
        val input = this.javaClass.classLoader?.getResourceAsStream(fileName)
        mockWebServer.enqueue(MockResponse().setResponseCode(200).
            setBody(input?.bufferedReader().use {it!!.readText()}))
    }

    fun setErrorResponse() {
        mockWebServer.enqueue(MockResponse().setResponseCode(400).setBody("{}"))
    }
}
