package com.example.property

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.property.Extension.Companion.atPosition
import com.example.property.Extension.Companion.first
import com.example.property.main.view.MainActivity
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matchers

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class UIFlowTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun checkViewTypeForEachPropertyInList() {
        Extension.waitUntilViewMatched(
            allOf(
                isDisplayed(),
                first(withParent(withId(R.id.recyclerView)))
            ),
            matches(isDisplayed()), 1000)

        onView(
            withId(R.id.recyclerView)
        ).check(
            matches(
                atPosition(0,
                    hasDescendant(
                        allOf(
                            withEffectiveVisibility(
                                Visibility.VISIBLE
                            ),
                            withId(R.id.highlightTextView),
                            withText("Highlight Today!")
                        )
                    )
                )
            )
        )

        onView(
            withId(R.id.recyclerView)
        ).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                1
            )
        )

        onView(
            withId(R.id.recyclerView)
        ).check(
            matches(
                atPosition(1,
                    hasDescendant(
                        allOf(
                            withEffectiveVisibility(
                                Visibility.VISIBLE
                            ),
                            withId(R.id.averagePriceTextView),
                            withText("6 950 000 kr")
                        ),
                    )
                )
            )
        )

        onView(
            withId(R.id.recyclerView)
        ).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                2
            )
        )

        onView(
            withId(R.id.recyclerView)
        ).check(
            matches(
                atPosition(2,
                    hasDescendant(
                        allOf(
                            withEffectiveVisibility(
                                Visibility.VISIBLE
                            ),
                            withId(R.id.titleTextView),
                            withText("Stockholm")
                        ),
                    )
                )
            )
        )
    }

    @Test
    fun checkDetailsScreenShowsAfterClickItemInPropertyList() {
        Extension.waitUntilViewMatched(
            Matchers.allOf(
                isDisplayed(),
                first(withParent(withId(R.id.recyclerView)))
            ),
            matches(isDisplayed()), 1000)
        onView(
            Matchers.allOf(
                isDisplayed(),
                first(withParent(withId(R.id.recyclerView)))
            )
        ).perform(click())

        onView(withText("A mockup screen to represent details of property")).check(
            matches(
                withEffectiveVisibility(
                    Visibility.VISIBLE
                )
            )
        )
    }
}
