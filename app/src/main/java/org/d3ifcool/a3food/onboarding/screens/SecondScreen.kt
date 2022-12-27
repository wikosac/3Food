package org.d3ifcool.a3food.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import org.d3ifcool.a3food.R
import org.d3ifcool.a3food.databinding.FirstScreenBinding
import org.d3ifcool.a3food.databinding.SecondScreenBinding

class SecondScreen : Fragment() {

    private lateinit var binding: SecondScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SecondScreenBinding.inflate(layoutInflater, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        binding.next.setOnClickListener {
            viewPager?.currentItem = 2
        }

        return binding.root
    }

}