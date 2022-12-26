package org.d3ifcool.a3food

import org.junit.Assert.*
import android.os.SystemClock
import androidx.core.content.ContextCompat
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import org.junit.Test

class LoginActivityTest {
    @Test
    fun clickTest() {
        // Jalankan Activity
        ActivityScenario.launch(LoginActivity::class.java)
        SystemClock.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.loginButton)).perform(ViewActions.click())
    }

    @Test
    fun next() {
        ActivityScenario.launch(LoginActivity::class.java)
        Espresso.onView(ViewMatchers.withId(R.id.lewati)).perform(ViewActions.click())
    }
}