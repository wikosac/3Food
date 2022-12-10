package org.d3ifcool.a3food

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3ifcool.a3food.data.Food
import org.d3ifcool.a3food.databinding.FoodListBinding

class MainAdapter(
    private val handler: ClickHandler
) : ListAdapter<Food, MainAdapter.ViewHolder>(DIFF_CALLBACK) {
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
    private val selectionIds = ArrayList<String>()
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
    fun resetSelection() {
        selectionIds.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: FoodListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(food: Food) = with(binding) {
            namaTextView.text = food.nama
            tokoTextView.text = food.toko
            hargaTextView.text = food.harga

            val pos = absoluteAdapterPosition
            itemView.isSelected = selectionIds.contains(food.id)
            itemView.setOnClickListener { handler.onClick(pos, food) }
            itemView.setOnLongClickListener { handler.onLongClick(pos) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FoodListBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface ClickHandler {
        fun onClick(position: Int, food: Food)
        fun onLongClick(position: Int) : Boolean
    }
}