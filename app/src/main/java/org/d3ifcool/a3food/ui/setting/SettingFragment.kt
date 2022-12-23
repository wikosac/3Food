package org.d3ifcool.a3food.ui.setting

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.d3ifcool.a3food.LoginActivity
import org.d3ifcool.a3food.ProfileEditFragment
import org.d3ifcool.a3food.R
import org.d3ifcool.a3food.databinding.FragmentSettingBinding


class SettingFragment : Fragment() {

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

        binding.logout?.setOnClickListener {
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
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
}