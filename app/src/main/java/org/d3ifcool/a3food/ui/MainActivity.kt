package org.d3ifcool.a3food.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import org.d3ifcool.a3food.R
import org.d3ifcool.a3food.data.FoodDb
import org.d3ifcool.a3food.databinding.ActivityMainBinding
import org.d3ifcool.a3food.databinding.FragmentSettingBinding

class MainActivity : AppCompatActivity() {

    private val contract = FirebaseAuthUIActivityResultContract()
    private val signInLauncher = registerForActivityResult(contract) {  }
    private val viewModel: MainViewModel by lazy {
        val dataSource = FoodDb.getInstance().dao
        val factory = MainViewModelFactory(dataSource)
        ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }
    private lateinit var binding: ActivityMainBinding
    private lateinit var bind: FragmentSettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        bind = FragmentSettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bind.login?.setOnClickListener { mulaiLogin() }
        viewModel.authState.observe(this, { updateUI(it) })

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

    private fun updateUI(user: FirebaseUser?) {
        bind.login!!.text = if (user == null)
            getString(R.string.login)
        else
            getString(R.string.logout)
    }

    private fun mulaiLogin() {
        if (bind.login!!.text == getString(R.string.logout)) {
            AuthUI.getInstance().signOut(this)
            return
        }

        val providers = arrayListOf(AuthUI.IdpConfig.GoogleBuilder().build())
        val intent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        signInLauncher.launch(intent)
    }
}