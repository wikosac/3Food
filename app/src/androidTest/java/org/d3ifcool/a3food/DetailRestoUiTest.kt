package org.d3ifcool.a3food

import android.os.SystemClock
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import org.junit.Test

class DetailRestoUiTest {
    @Test
    fun clickTest() {
        // Jalankan Activity
        ActivityScenario.launch(MainActivity::class.java)

        SystemClock.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView)).perform(ViewActions.click())
    }


}