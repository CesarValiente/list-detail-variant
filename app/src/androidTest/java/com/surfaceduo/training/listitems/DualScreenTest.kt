/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License.
 *
 */

package com.surfaceduo.training.listitems

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.UiDevice
import com.surfaceduo.training.listitems.recyclerview.ItemViewHolder
import org.hamcrest.core.AllOf
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class DualScreenTest () {

    @get:Rule
    val activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun should_detail_content_be_correct() {
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.swipe(675, 1780, 1350, 900, 400)

        onView(withId(R.id.first_container_id)).check(matches(isDisplayed()))
        onView(withId(R.id.second_container_id)).check(matches(isDisplayed()))

        onView(withId(R.id.list_items))
            .perform(RecyclerViewActions.actionOnItem<ItemViewHolder>(
                hasDescendant(withText("This is number 2")), click()))

        onView(allOf(withId(R.id.detail_number), withText("2"))).check(matches(isDisplayed()))
    }

}