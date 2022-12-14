package org.d3ifcool.a3food.ui.search

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.d3ifcool.a3food.data.Key
import org.d3ifcool.a3food.databinding.FoodListBinding
import org.d3ifcool.a3food.ui.dashboard.DashboardAdapter

class SearchAdapter(private val data: List<Key>) :
    RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    class ViewHolder(
        private val binding: FoodListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

//        fun bind() = with(binding) {
//            namaTextView.text = food.nama
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardAdapter.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: DashboardAdapter.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}