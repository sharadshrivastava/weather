package com.test.app.ui

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import kotlinx.android.synthetic.main.main_activity.*

import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    @Rule @JvmField
    var rule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testFragmentLoaded() {
        val fragment = rule.scenario.onActivity {
            val fragment = it.navHostFragment
            Assert.assertNotNull(fragment)
        }
    }
}
