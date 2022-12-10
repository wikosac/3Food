package org.d3ifcool.a3food.ui.dashboard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import org.d3ifcool.a3food.MainAdapter
import org.d3ifcool.a3food.data.Food
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
        //viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

        Log.d("dasbor", "Jumlah data: " + getData().size)

        with(binding.recyclerView) {
            addItemDecoration(DividerItemDecoration(context, androidx.recyclerview.widget.RecyclerView.VERTICAL))
            adapter = DashboardAdapter(getData())
            setHasFixedSize(true)
        }
    }

    private fun getData(): List<Food> {
        return listOf(
            Food("1","Nasi Goreng", "Warung Pak Rebo", "Rp15000"),
            Food("2","Nasi Goreng", "Warung Pak Rebo", "Rp15000"),
            Food("3","Nasi Goreng", "Warung Pak Rebo", "Rp15000"),
            Food("4","Nasi Goreng", "Warung Pak Rebo", "Rp15000")
        )
    }
}