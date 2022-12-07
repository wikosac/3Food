package org.d3ifcool.a3food

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    companion object {
        private val keyword = "Nasi Goreng"
    }

    @Test
    fun testClick() {
        // Jalankan Activity
        val activityScenario = ActivityScenario.launch(
            MainActivity::class.java
        )

        // Lakukan aksi klik navigasi
        onView(withId(R.id.navigation_search)).perform(click())
        Thread.sleep(1000)
        onView(withId(R.id.searchView)).perform(click())
        onView(withId(R.id.searchView)).perform(typeText(keyword))
        Thread.sleep(1000)
        onView(withId(R.id.navigation_setting)).perform(click())
        onView(withId(R.id.navigation_home)).perform(click())

        // Tes selesai, tutup activity nya
        Thread.sleep(30000)
        //activityScenario.close()
    }
}