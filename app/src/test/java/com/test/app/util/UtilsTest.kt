package com.test.app.util

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

import org.junit.Assert.assertTrue

@RunWith(MockitoJUnitRunner::class)
class UtilsTest {

    @Mock
    var context: Context? = null
    @Mock
    var resources: Resources? = null
    @Mock
    var displayMetrics: DisplayMetrics? = null

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)

        Mockito.`when`(context?.resources).thenReturn(resources)
        Mockito.`when`(resources?.displayMetrics).thenReturn(displayMetrics)
        displayMetrics?.density = 5f
    }

    @Test
    fun testPxFromDp() {
        val expected = 80f
        val actual = Utils.pxFromDp(context, 16f)
        assertTrue(expected == actual)
    }
}