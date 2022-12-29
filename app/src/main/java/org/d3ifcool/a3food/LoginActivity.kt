package org.d3ifcool.a3food

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import org.d3ifcool.a3food.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val contract = FirebaseAuthUIActivityResultContract()
    private val signInLauncher = registerForActivityResult(contract) {this.onSignInResult(it) }
    private val authState = FirebaseUserLiveData()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.loginButton.setOnClickListener { mulaiLogin() }
        binding.lewati.setOnClickListener { lewati() }
        authState.observe(this, { updateUI(it) })
    }

    private fun mulaiLogin() {
        if (binding.loginButton.text == getString(R.string.logout)) {
            AuthUI.getInstance().signOut(this)
            return
        }
        val providers = arrayListOf(AuthUI.IdpConfig.GoogleBuilder().build())
        val intent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        signInLauncher.launch(intent)
//        val intentZ = Intent(this, MainActivity::class.java)
//        startActivity(intentZ)
//        finish()
    }

    private fun lewati() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    @SuppressLint("SuspiciousIndentation")
    private fun updateUI(user: FirebaseUser?) {
        binding.loginButton.text = if (user == null)
            getString(R.string.login)
        else
            getString(R.string.logout)
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            val nama = FirebaseAuth.getInstance().currentUser?.displayName
            Log.i("LOGIN", "$nama berhasil login")
        } else {
            Log.i("LOGIN", "Login gagal: ${response?.error?.errorCode}")
        }
    }
}