package com.example.property

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import java.lang.Thread.sleep


class Extension {
    companion object {
        fun waitUntilViewMatched(
            viewMatcher: Matcher<View>,
            assertion: ViewAssertion,
            durationInMillis: Long
        ) {
            val startTime = System.currentTimeMillis()
            while (true) {
                var vi: ViewInteraction? = null
                try {
                    vi = onView(viewMatcher)
                } catch (e: Throwable) {
                    //Do nothing
                }

                if (vi != null) {
                    try {
                        vi.check(assertion)
                        break
                    } catch (e: Throwable) {
                        //Do nothing
                    }

                }
                val diff = System.currentTimeMillis() - startTime
                if (diff > durationInMillis) {
                    assertThat<Any>(
                        "waitUntilViewMatched() Timeout reached (Aborted)",
                        true, Matchers.not(Matchers.anything())
                    )
                }
                sleep(100)
            }
        }


        fun <T> first(matcher: Matcher<T>): Matcher<T>? {
            return object : BaseMatcher<T>() {
                var isFirst = true
                override fun matches(item: Any): Boolean {
                    if (isFirst && matcher.matches(item)) {
                        isFirst = false
                        return true
                    }
                    return false
                }

                override fun describeTo(description: Description) {
                    description.appendText("Should return first matching item")
                }
            }
        }
        fun <T> atPosition(position: Int, matcher: Matcher<T>): BoundedMatcher<View?, RecyclerView> {
            return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
                override fun describeTo(description: Description) {
                    description.appendText("has item at position $position: ")
                    matcher.describeTo(description)
                }

                override fun matchesSafely(view: RecyclerView): Boolean {
                    val viewHolder = view.findViewHolderForAdapterPosition(position)
                        ?: // has no item on such position
                        return false
                    return matcher.matches(viewHolder.itemView)
                }
            }
        }
    }
}