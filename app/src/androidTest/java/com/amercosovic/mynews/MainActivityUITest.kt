package com.amercosovic.mynews

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    /* Instantiate an ActivityScenarioRule object. */
    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun testTabClicks() {
        // Click on a tab
        onView(withText("Top Stories")).perform(click());
        // see if fragment is displayed
        onView(withId(R.id.frame_layout)).check(matches(isDisplayed()))

        // Click on a tab
        onView(withText("Most Popular")).perform(click());
        // see if fragment is displayed
        onView(withId(R.id.frame_layout)).check(matches(isDisplayed()))

        // Click on a tab
        onView(withText("Sports")).perform(click());
        // see if fragment is displayed
        onView(withId(R.id.frame_layout)).check(matches(isDisplayed()))
    }

    @Test
    fun testSearchIconClick() {
        // click on search icon
        onView(withId(R.id.searchIcon)).perform(click())
        // see if search activity opens up
        intended(hasComponent(SearchActivity::class.java.name))
    }

    @Test
    fun notificationMenuClick() {
        // click on notifications menu option
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext())
        onView(withText("Notifications")).perform(click());
        // see if notifications activity opens up
        intended(hasComponent(NotificationsActivity::class.java.name))
    }

}