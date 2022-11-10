package org.d3ifcool.a3food

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import org.d3ifcool.a3food.databinding.ActivityMainBinding
import org.d3ifcool.a3food.databinding.FragmentDashboardBinding

class MainActivity : AppCompatActivity() {

    private val contract = FirebaseAuthUIActivityResultContract()
    private val signInLauncher = registerForActivityResult(contract) { }
    private lateinit var binding: ActivityMainBinding
    private lateinit var bind: FragmentDashboardBinding
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.login.setOnClickListener { mulaiLogin() }

        viewModel.authState.observe(this, { updateUI(it) })
    }

    private fun mulaiLogin() {
        bind.profile.setOnClickListener() {
            AuthUI.getInstance().signOut(this)
        }

        val providers = arrayListOf(AuthUI.IdpConfig.GoogleBuilder().build())
        val intent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        signInLauncher.launch(intent)
    }

    private fun updateUI(user: FirebaseUser?) {
        val intent = Intent(this, DashboardActivity::class.java)

        if (user == null)
            binding.login.text = getString(R.string.login)
        else
            startActivity(intent)
            finish()
    }
}