package org.d3ifcool.a3food

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3ifcool.a3food.databinding.FragmentDashboardBinding
import org.d3ifcool.a3food.ui.dashboard.DashboardFragment

class DashboardActivity : AppCompatActivity() {
    //private lateinit var binding: FragmentDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = FragmentDashboardBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_dashboard)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DashboardFragment.newInstance())
                .commitNow()
        }
    }

}