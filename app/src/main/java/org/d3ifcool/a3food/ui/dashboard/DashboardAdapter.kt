package org.d3ifcool.a3food.ui.dashboard

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.d3ifcool.a3food.data.Food
import org.d3ifcool.a3food.databinding.FoodListBinding
import org.d3ifcool.a3food.ui.DetailActivity
import org.d3ifcool.a3food.ui.search.SearchActivity

class DashboardAdapter(private val data: List<Food>) :
    RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    class ViewHolder(
        private val binding: FoodListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(food: Food) = with(binding) {
            namaTextView.text = food.nama
            tokoTextView.text = food.toko
            hargaTextView.text = food.harga
            root.setOnClickListener {
                val intent = Intent(root.context, DetailActivity::class.java)
//                intent.putExtra()
                root.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FoodListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}