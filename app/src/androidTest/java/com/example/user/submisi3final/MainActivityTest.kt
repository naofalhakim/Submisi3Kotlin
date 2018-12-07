package com.example.user.submisi3final

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.example.user.submisi3final.R.id.*
import com.example.user.submisi3final.view.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testRecyclerViewBehaviour() {
        onView(withId(mylist))
            .check(matches(isDisplayed()))
        onView(withId(mylist)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(mylist)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
    }

    @Test
    fun testAddMatchFavorite() {
        onView(ViewMatchers.withText("Everton"))
            .check(matches(isDisplayed()))
        onView(ViewMatchers.withText("Everton")).perform(click())

        onView(withId(add_to_favorite))
            .check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())

        onView(ViewMatchers.withText("Added to favorite"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testRemoveMatchFavorite() {
        onView(withId(bottom_navigation))
            .check(matches(isDisplayed()))
        onView(withId(favorites)).perform(click())

        onView(ViewMatchers.withText("Everton"))
            .check(matches(isDisplayed()))
        onView(ViewMatchers.withText("Everton")).perform(click())

        onView(withId(add_to_favorite))
            .check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())

        onView(ViewMatchers.withText("Removed to favorite"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testAppBehaviourBottom() {
        onView(withId(bottom_navigation))
            .check(matches(isDisplayed()))
        onView(withId(favorites)).perform(click())
        onView(withId(next_match)).perform(click())
        onView(withId(teams)).perform(click())
    }


}