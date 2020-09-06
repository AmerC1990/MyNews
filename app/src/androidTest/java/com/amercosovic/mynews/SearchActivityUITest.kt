package com.amercosovic.mynews

import android.provider.ContactsContract
import android.provider.ContactsContract.Directory.PACKAGE_NAME
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.bumptech.glide.Glide.init
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.EnumSet.allOf

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(AndroidJUnit4::class)
class SearchActivityTest {
    @get:Rule
    var activityRule: ActivityScenarioRule<SearchActivity> =
        ActivityScenarioRule(SearchActivity::class.java)

    @Before
    fun setUp() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun enterQueryAndClickSearch() {
        // Type a word into the search query edit text
        // and test if that word displays
        onView(withId(R.id.search_query_edittext))
            .perform(click())
            .perform(typeText("Trump"))
            .check(matches(withText("Trump")))

        // test if checkbox gets checked
        onView(withId(R.id.PoliticsTextBox)).perform(click()).check(matches(isChecked()))

        // click search button and
        // Verifies that Results Activity is opened
        onView(withId(R.id.searchButton)).perform(click())
        intended(hasComponent(ResultsActivity::class.java.name))
    }

    @Test
    fun enterQueryAndClickSearchWith2Checkboxes() {
        // Type a word into the search query edit text
        // and test if that word displays
        Espresso.onView(ViewMatchers.withId(R.id.search_query_edittext))
            .perform(ViewActions.click())
            .perform(ViewActions.typeText("Trump"))
            .check(ViewAssertions.matches(ViewMatchers.withText("Trump")))

        // test if checkbox gets checked
        Espresso.onView(ViewMatchers.withId(R.id.PoliticsTextBox))
            .perform(ViewActions.click()).check(ViewAssertions.matches(ViewMatchers.isChecked()))

        // test if checkbox gets checked
        Espresso.onView(ViewMatchers.withId(R.id.BusinessTextBox))
            .perform(ViewActions.click()).check(ViewAssertions.matches(ViewMatchers.isChecked()))

        // click search button and
        // Verifies that Results Activity is opened
        Espresso.onView(ViewMatchers.withId(R.id.searchButton)).perform(ViewActions.click())
        Intents.intended(IntentMatchers.hasComponent(ResultsActivity::class.java.name))
    }
}
//    fun useAppContext() {
//        // Context of the app under test.
//        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//        assertEquals("com.amercosovic.mynews", appContext.packageName)
//    }

