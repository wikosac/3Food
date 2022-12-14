package org.d3ifcool.a3food.ui.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.d3ifcool.a3food.R
import org.d3ifcool.a3food.databinding.ActivitySearchBinding

class SearchFragment : Fragment() {

    private lateinit var binding: ActivitySearchBinding

    companion object {
        fun newInstance() = SearchFragment()
    }

    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivitySearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.magniGlass.setOnClickListener {
            val keyword = binding.searchView.text.toString()
            binding.testOutput.text = keyword
        }
    }
}