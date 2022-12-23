package org.d3ifcool.a3food

import android.os.SystemClock
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.d3ifcool.a3food.data.Key
import org.junit.Test

class MainActivityTest {
    companion object {
        private val key1 = Key("1", "Nasi goreng")
        private val key2 = Key("2", "")
        private val key3 = Key("3", "1234")
        private val key4 = Key("4", "!@#$")
    }

    @Test
    fun testSearch() {
        // Jalankan MainActivity
        ActivityScenario.launch(MainActivity::class.java)

        // input data to firebase realtime database
        onView(withId(R.id.navigation_search)).perform(click())
        onView(withId(R.id.searchView)).perform(click())

        onView(withId(R.id.searchView)).perform(typeText(key1.keyword))
        SystemClock.sleep(2000)
        onView(withId(R.id.magniGlass)).perform(click())
        SystemClock.sleep(2000)
        onView(withId(R.id.searchView)).perform(clearText())

        onView(withId(R.id.searchView)).perform(typeText(key2.keyword))
        SystemClock.sleep(2000)
        onView(withId(R.id.magniGlass)).perform(click())
        SystemClock.sleep(2000)
        onView(withId(R.id.searchView)).perform(clearText())

        onView(withId(R.id.searchView)).perform(typeText(key3.keyword))
        SystemClock.sleep(2000)
        onView(withId(R.id.magniGlass)).perform(click())
        SystemClock.sleep(2000)
        onView(withId(R.id.searchView)).perform(clearText())

        onView(withId(R.id.searchView)).perform(typeText(key4.keyword))
        SystemClock.sleep(2000)
        onView(withId(R.id.magniGlass)).perform(click())
        SystemClock.sleep(2000)
    }
}