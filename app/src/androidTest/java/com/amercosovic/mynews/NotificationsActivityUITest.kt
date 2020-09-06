package com.amercosovic.mynews

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NotificationsActivityUITest {
    @get:Rule
    var activityRule: ActivityScenarioRule<NotificationsActivity> =
        ActivityScenarioRule(NotificationsActivity::class.java)

    @Before
    fun setUp() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun enterQueryAndEnableAndDisableNotifications() {
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

        // turn enable notifications toggle/switch on
        // Verifies that it has been turned/switched on
        Espresso.onView(ViewMatchers.withId(R.id.enableNotificationsSwitch)).perform(ViewActions.click())
            .check(matches(ViewMatchers.isChecked()))

        // turn enable notifications toggle/switch off
        // Verifies that it has been turned/switched off
        Espresso.onView(ViewMatchers.withId(R.id.enableNotificationsSwitch)).perform(ViewActions.click())
            .check(matches(ViewMatchers.isNotChecked()))

        // verify that query has been deleted and that checkboxes have been unchecked
        Espresso.onView(ViewMatchers.withId(R.id.search_query_edittext))
            .perform(ViewActions.click())
            .perform(ViewActions.clearText())
            .check(ViewAssertions.matches(ViewMatchers.withText("")))
    }
}