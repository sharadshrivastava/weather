package com.test.app.ui


import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.test.app.R
import org.hamcrest.CoreMatchers.anything
import org.hamcrest.CoreMatchers.containsString
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * This class is for Android UI testing using Espresso.
 */

@RunWith(AndroidJUnit4::class)
@LargeTest
class HomeFragmentTest {

    private val UILOAD_DELAY = 1000L

    @Rule
    @JvmField
    var rule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setup() {
        Thread.sleep(UILOAD_DELAY)
    }

    @Test
    fun testSubmitIsVisible() {
        onView(withId(R.id.submitBtn)).check(matches(isDisplayed()))
    }

    @Test
    fun testProgressIsGone() {
        onView (withId(R.id.loading)).check(matches(
            withEffectiveVisibility(Visibility.GONE)));
    }

    @Test
    fun testEditTextIsVisible() {
        onView(withId(R.id.searchEt)).check(matches(isDisplayed()))
    }

    @Test
    fun testSpinnerSelection(){
        onView(withId(R.id.searchSpinner)).perform(click());
        onData(anything()).atPosition(1).perform(click());
        onView(withId(R.id.searchSpinner)).check(matches(withSpinnerText(containsString("Zip Code"))));
    }

    @Test
    fun testEditTextDefaultHint(){
        onView(withId(R.id.searchEt)).check(matches(withHint("Enter City, Country")))
    }

    //In the same way, using espresso we can test other UI components.
}
