package org.d3ifcool.a3food.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.d3ifcool.a3food.R

class OnboardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        supportActionBar?.hide()
    }
}