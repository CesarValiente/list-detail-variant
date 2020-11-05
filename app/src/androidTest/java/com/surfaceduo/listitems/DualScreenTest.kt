package com.surfaceduo.listitems

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.UiDevice
import com.surfaceduo.listitems.recyclerview.ItemViewHolder
import org.hamcrest.core.AllOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class DualScreenTest() {

    @get:Rule
    val activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun should_detail_content_be_correct() {
        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device.swipe(675, 1780, 1350, 900, 400)

        Espresso.onView(ViewMatchers.withId(R.id.first_container_id))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.second_container_id))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.list_items))
            .perform(
                RecyclerViewActions.actionOnItem<ItemViewHolder>(
                    ViewMatchers.hasDescendant(ViewMatchers.withText("This is number 2")), ViewActions.click()
                )
            )

        Espresso.onView(AllOf.allOf(ViewMatchers.withId(R.id.detail_number), ViewMatchers.withText("2")))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}