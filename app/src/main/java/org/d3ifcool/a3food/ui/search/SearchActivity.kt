package org.d3ifcool.a3food.ui.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.d3ifcool.a3food.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {

    private lateinit var viewModel: SearchViewModel
    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}