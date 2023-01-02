package org.d3ifcool.a3food.ui.dashboard

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3ifcool.a3food.data.Food
import org.d3ifcool.a3food.databinding.FoodListBinding
import org.d3ifcool.a3food.DetailActivity
import org.d3ifcool.a3food.R
import org.d3ifcool.a3food.network.PlaceApi

class DashboardAdapter(private val handler: ClickHandler) :
    ListAdapter<Food, DashboardAdapter.ViewHolder>(DIFF_CALLBACK) {

    private val selectionIds = ArrayList<String>()

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Food>() {
            override fun areItemsTheSame(
                oldData: Food, newData: Food
            ): Boolean {
                return oldData.id == newData.id
            }
            override fun areContentsTheSame(
                oldData: Food, newData: Food
            ): Boolean {
                return oldData == newData
            }
        }
    }

    interface ClickHandler {
        fun onClick(position: Int, food: Food)
        fun onLongClick(position: Int) : Boolean
    }

    inner class ViewHolder(
        private val binding: FoodListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(food: Food) = with(binding) {
            tokoTextView.text = food.toko
            alamatTextView.text = food.alamat
            ratingTextView.text = food.rating
            Glide.with(imageList.context)
                .load(PlaceApi.getPlaceUrl(food.toko))
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(imageList)

            val pos = absoluteAdapterPosition
            itemView.isSelected = selectionIds.contains(food.id)
            itemView.setOnClickListener { handler.onClick(pos, food) }
            itemView.setOnLongClickListener { handler.onLongClick(pos) }

            itemView.setOnClickListener {
                val intent = Intent(root.context, DetailActivity::class.java)
                intent.putExtra("toko", food.toko)
                intent.putExtra("alamat", food.alamat)
                intent.putExtra("rating", food.rating)
                intent.putExtra("img", PlaceApi.getPlaceUrl(food.toko))
                root.context.startActivity(intent)
            }
        }
    }

    fun toggleSelection(pos: Int) {
        val id = getItem(pos).id
        if (selectionIds.contains(id))
            selectionIds.remove(id)
        else
            selectionIds.add(id)
        notifyDataSetChanged()
    }

    fun getSelection(): List<String> {
        return selectionIds
    }

    @SuppressLint("NotifyDataSetChanged")
    fun resetSelection() {
        selectionIds.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FoodListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}