package org.d3ifcool.a3food.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import org.d3ifcool.a3food.databinding.FragmentSearchBinding


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

        binding.searchView.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                performSearch()
                return@OnEditorActionListener true
            }
            false
        })
    }

    fun performSearch() {
        val keyword = binding.searchView.text.toString()
        val validKey = validation(keyword)
        binding.testOutput.text = validKey
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

        return "Hasil Pencarian: $key"
    }
}