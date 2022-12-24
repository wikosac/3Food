package org.d3ifcool.a3food.ui.setting

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.registerForActivityResult
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import org.d3ifcool.a3food.LoginActivity
import org.d3ifcool.a3food.ui.ProfileEditFragment
import org.d3ifcool.a3food.R
import org.d3ifcool.a3food.databinding.FragmentSettingBinding


class SettingFragment : Fragment() {

    private val contract = FirebaseAuthUIActivityResultContract()
    private val signInLauncher = activity?.registerForActivityResult(contract) { }
    private lateinit var binding: FragmentSettingBinding

    companion object {
        fun newInstance() = SettingFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateUI(FirebaseAuth.getInstance().getCurrentUser())

        binding.logout?.setOnClickListener {
            if (binding.logout?.text == getString(R.string.logout)) {
                AuthUI.getInstance().signOut(requireContext())
                Toast.makeText(requireContext(), "Berhasil Logout", Toast.LENGTH_SHORT).show()
            }
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
//            val providers = arrayListOf(AuthUI.IdpConfig.GoogleBuilder().build())
//            val intent = AuthUI.getInstance()
//                .createSignInIntentBuilder()
//                .setAvailableProviders(providers)
//                .build()
//            signInLauncher?.launch(intent)
        }
        binding.ubahProfile!!.setOnClickListener {
            val fragment: Fragment = ProfileEditFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .add(fragment, "editP") // Add this transaction to the back stack (name is an optional name for this back stack state, or null).
                .addToBackStack(null)
                .replace(R.id.container, fragment)
                .commit()
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        binding.logout?.text = if (user == null)
            getString(R.string.login)
        else
            getString(R.string.logout)
    }
}