package org.d3ifcool.a3food.ui.dashboard

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import org.d3ifcool.a3food.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    //private lateinit var viewModel: DashboardViewModel

    companion object {
        fun newInstance() = DashboardFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this)
            .get(DashboardViewModel::class.java)

        Log.d("dasbor", "Jumlah data: " + viewModel.getData().size)

        with(binding.recyclerView) {
            addItemDecoration(
                DividerItemDecoration(context, androidx.recyclerview.widget.RecyclerView.VERTICAL)
            )
            adapter = DashboardAdapter(viewModel.getData())
            setHasFixedSize(true)
        }
    }
}