package com.test.app

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.test.app.data.AppRepository
import com.test.app.di.DaggerTestComponent
import com.test.app.di.TestModule
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Rule
import org.junit.rules.TestRule
import javax.inject.Inject


abstract class BaseTest {

    @Inject
    lateinit var mockWebServer : MockWebServer

    @Inject
    lateinit var appRepository: AppRepository

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    fun setUp() {
        val component = DaggerTestComponent.builder()
                .testModule(TestModule()).build()
        component.inject(this)
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
