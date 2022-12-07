package org.d3ifcool.a3food.ui.dashboard

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3ifcool.a3food.DashboardAdapter
import org.d3ifcool.a3food.Food
import org.d3ifcool.a3food.R
import org.d3ifcool.a3food.databinding.ActivityMainBinding
import org.d3ifcool.a3food.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    companion object {
        fun newInstance() = DashboardFragment()
    }

    private lateinit var binding: FragmentDashboardBinding
    //private lateinit var viewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = FragmentDashboardBinding.inflate(layoutInflater)
        //viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        // TODO: Use the ViewModel

        Log.d("dasbor", "Jumlah data: " + getData().size)

        with(binding.recyclerView) {
            addItemDecoration(DividerItemDecoration(context, androidx.recyclerview.widget.RecyclerView.VERTICAL))
            adapter = DashboardAdapter(getData())
            setHasFixedSize(true)
        }


    }

    private fun getData(): List<Food> {
        return listOf(
            Food("Nasi Goreng", "Warung Pak Rebo", "Rp15000"),
            Food("Nasi Goreng", "Warung Pak Rebo", "Rp15000"),
            Food("Nasi Goreng", "Warung Pak Rebo", "Rp15000"),
            Food("Nasi Goreng", "Warung Pak Rebo", "Rp15000")
        )
    }
}