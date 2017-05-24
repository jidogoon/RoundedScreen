package com.jidogoon.roundedscreen

import android.os.Build
import android.support.annotation.RequiresApi
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4

import com.jidogoon.roundedscreen.R
import com.jidogoon.roundedscreen.activity.MainSettingsActivity

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.*
import android.view.View
import android.support.v7.widget.RecyclerView.ViewHolder
import org.hamcrest.Matchers.allOf

@RunWith(AndroidJUnit4::class)
class MainSettingsEspressoTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainSettingsActivity::class.java)

    @RequiresApi(Build.VERSION_CODES.N)
    @Test
    fun mainSettingsEspressoTest() {
        val recyclerView = onView(
                allOf<View>(withId(R.id.list),
                        withParent(withId(android.R.id.list_container)),
                        isDisplayed()))
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(1, click()))

        val recyclerView2 = onView(
                allOf<View>(withId(R.id.list),
                        withParent(withId(android.R.id.list_container)),
                        isDisplayed()))
        recyclerView2.perform(actionOnItemAtPosition<ViewHolder>(1, click()))

        val recyclerView3 = onView(
                allOf<View>(withId(R.id.list),
                        withParent(withId(android.R.id.list_container)),
                        isDisplayed()))
        recyclerView3.perform(actionOnItemAtPosition<ViewHolder>(1, click()))

        val recyclerView4 = onView(
                allOf<View>(withId(R.id.list),
                        withParent(withId(android.R.id.list_container)),
                        isDisplayed()))
        recyclerView4.perform(actionOnItemAtPosition<ViewHolder>(5, click()))

        val recyclerView5 = onView(
                allOf<View>(withId(R.id.list),
                        withParent(withId(android.R.id.list_container)),
                        isDisplayed()))
        recyclerView5.perform(actionOnItemAtPosition<ViewHolder>(5, click()))

        val recyclerView6 = onView(
                allOf<View>(withId(R.id.list),
                        withParent(withId(android.R.id.list_container)),
                        isDisplayed()))
        recyclerView6.perform(actionOnItemAtPosition<ViewHolder>(5, click()))

        val recyclerView7 = onView(
                allOf<View>(withId(R.id.list),
                        withParent(withId(android.R.id.list_container)),
                        isDisplayed()))
        recyclerView7.perform(actionOnItemAtPosition<ViewHolder>(5, click()))

        val recyclerView8 = onView(
                allOf<View>(withId(R.id.list),
                        withParent(withId(android.R.id.list_container)),
                        isDisplayed()))
        recyclerView8.perform(actionOnItemAtPosition<ViewHolder>(8, click()))

        val recyclerView9 = onView(
                allOf<View>(withId(R.id.list),
                        withParent(withId(android.R.id.list_container)),
                        isDisplayed()))
        recyclerView9.perform(actionOnItemAtPosition<ViewHolder>(8, click()))

        val recyclerView10 = onView(
                allOf<View>(withId(R.id.list),
                        withParent(withId(android.R.id.list_container)),
                        isDisplayed()))
        recyclerView10.perform(actionOnItemAtPosition<ViewHolder>(2, click()))

        val recyclerView11 = onView(
                allOf<View>(withId(R.id.list),
                        withParent(withId(android.R.id.list_container)),
                        isDisplayed()))
        recyclerView11.perform(actionOnItemAtPosition<ViewHolder>(2, click()))

    }

}
