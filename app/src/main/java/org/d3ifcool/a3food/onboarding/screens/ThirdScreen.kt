package org.d3ifcool.a3food.onboarding.screens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import org.d3ifcool.a3food.LoginActivity
import org.d3ifcool.a3food.MainActivity
import org.d3ifcool.a3food.R
import org.d3ifcool.a3food.databinding.ThirdScreenBinding

class ThirdScreen : Fragment() {
    private lateinit var binding: ThirdScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = ThirdScreenBinding.inflate(layoutInflater, container, false)

        binding.selesai.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_loginActivity)
            onBoardingFinished()
//            val intent = Intent(activity, LoginActivity::class.java)
//            startActivity(intent)
//            activity?.finish()
        }

        return binding.root
    }

    private fun onBoardingFinished() {
        val sharedPreferences = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }
}