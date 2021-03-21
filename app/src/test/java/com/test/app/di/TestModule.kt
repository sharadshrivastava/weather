package com.test.app.di

import com.test.app.data.db.AppDao
import com.test.app.data.network.WeatherApi
import dagger.Module
import dagger.Provides
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class TestModule {

    @Provides
    @Singleton
    fun mockServer(): MockWebServer = MockWebServer()

    @Provides
    @Singleton
    fun retrofit(mockWebServer: MockWebServer): Retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("").toString())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun api(retrofit: Retrofit): WeatherApi = retrofit.create(
        WeatherApi::class.java)


    //DB testing will be done in "androidTest" pkg as that needs Context.
    //We can get ApplicationProvider or InstrumentationRegistry only in "androidTest" pkg.
    // Returning null from here for satisfying repository param.
    @Provides
    @Singleton
    fun dao(): AppDao? = null
}