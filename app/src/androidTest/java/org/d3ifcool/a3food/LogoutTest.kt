package org.d3ifcool.a3food

import android.os.SystemClock
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.Test

class LogoutTest {


    @Test
    fun clickTest() {
        // Jalankan Activity
        ActivityScenario.launch(MainActivity::class.java)

        Espresso.onView(ViewMatchers.withId(R.id.navigation_setting)).
        perform(ViewActions.click())
        SystemClock.sleep(3000)

        Espresso.onView(ViewMatchers.withId(R.id.loginActivity)).perform(ViewActions.click())
        // Jalankan LoginActivity
                ActivityScenario.launch(LoginActivity::class.java)
    }

}