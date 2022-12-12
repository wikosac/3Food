package org.d3ifcool.a3food.ui.dashboard

import androidx.lifecycle.ViewModel
import org.d3ifcool.a3food.data.Food

class DashboardViewModel : ViewModel() {

    fun getData(): List<Food> {
        return listOf(
            Food("1","Nasi Goreng", "Warung Pak Rebo", "Rp15000"),
            Food("2","Nasi Goreng", "Warung Pak Rebo", "Rp15000"),
            Food("3","Nasi Goreng", "Warung Pak Rebo", "Rp15000"),
            Food("4","Nasi Goreng", "Warung Pak Rebo", "Rp15000")
        )
    }
}