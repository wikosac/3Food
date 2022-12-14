package org.d3ifcool.a3food.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.d3ifcool.a3food.R
import org.d3ifcool.a3food.data.FoodDb
import org.d3ifcool.a3food.databinding.ActivityMainBinding
import org.d3ifcool.a3food.databinding.FragmentSettingBinding
import org.d3ifcool.a3food.ui.dashboard.DashboardViewModelFactory

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lazy {
        val dataSource = FoodDb.getInstance().dao
        val factory = DashboardViewModelFactory(dataSource)
        ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }
    private lateinit var binding: ActivityMainBinding
    private lateinit var bind: FragmentSettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        bind = FragmentSettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // bottom navbar
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

        fun navigateToMaps() {
            navController.navigate(R.id.action_navigation_search_to_mapsFragment)
        }

//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, DashboardFragment.newInstance())
//                .commitNow()
//        }
    }
}