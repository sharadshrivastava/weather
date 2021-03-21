package com.test.app.service

import com.test.app.BaseTest
import com.test.app.data.network.wrapper.Resource
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AppRepositoryTest : BaseTest() {

    @Before
    fun setup() {
        super.setUp()
    }

    //In response json file, Sydney data is available so checking with "Sydney" name.
    @Test
    fun testSuccessResponse() {
        setResponse("response.json")
        runBlocking {
            Assert.assertTrue(appRepository.weatherByCity("sydney").data?.
                name.equals("Sydney"))
        }
    }

    @Test
    fun testFailResponse() {
        setErrorResponse()
        runBlocking {
            Assert.assertTrue( appRepository.weatherByCity("sydney").
                status!=Resource.Status.SUCCESS)
        }
    }

    @Test
    fun weatherByZipCode() {
        setResponse("response.json")
        runBlocking {
            Assert.assertTrue(appRepository.weatherByCity("2000,au").data?.
                name.equals("Sydney"))
        }
    }

    //In this way we can test other functionality as well using mock webserver and dummy response.
}