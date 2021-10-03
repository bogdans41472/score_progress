package com.bogdan.score

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.bogdan.score.main.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainFragmentTest {

    @get:Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Before
    fun setUp() {
        activityTestRule.launchActivity(null)
    }

    @Test
    fun launchApp_THEN_check_Score() {
        onView(withId(R.id.userScore_text))
            .check(matches(withText(credit_score_static_text)))
            .check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.currentUserScore))
            .check(matches(withText(current_user_score_value)))
            .check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.maxUserScore))
            .check(matches(withText(max_user_score_text)))
            .check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.progress_bar)).check(matches(isCompletelyDisplayed()))
    }

    companion object {
        private const val credit_score_static_text = "Your credit score is"
        private const val current_user_score_value = "514"
        private const val max_user_score_text = "out of 700"
    }
}