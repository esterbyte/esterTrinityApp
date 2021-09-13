package com.ester.esterTrinityApp.presentation.login

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.ester.esterTrinityApp.R
import com.ester.esterTrinityApp.presentation.LoginActivity
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class LoginActivityTest {

    @Test
    fun is_Activity_visible(){
        val activityScenario = ActivityScenario.launch(LoginActivity::class.java)
        onView(withId(R.id.loginActivity)).check(matches(isDisplayed()))
    }
}