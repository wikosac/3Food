package org.d3ifcool.a3food.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModelProvider
import org.d3ifcool.a3food.data.FoodDb
import org.d3ifcool.a3food.databinding.FragmentSearchBinding
import org.d3ifcool.a3food.ui.dashboard.DashboardViewModel
import org.d3ifcool.a3food.ui.dashboard.DashboardViewModelFactory

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
//    private val viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.magniGlass.setOnClickListener {
            val keyword = binding.searchView.text.toString()
            val validKey = validation(keyword)
            binding.testOutput.text = validKey
        }
    }

    fun validation(key: String): String {
        // Pengecekan kalo inputnya kosong
        if (key.isEmpty()) {
            return "Inputan gaboleh kosong"
        }

        // kalo inputan berupa angka (tapi lu pake string / text)
        if (key.isDigitsOnly()) {
            return "Inputan gabisa angka"
        }

        // kalo inputan simbol
        if (key.contains("[!\"#$%&'()*+,-./:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex())) {
            return "Inputan gabisa simbol"
        }

        return key
    }
}