package org.d3ifcool.a3food

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3ifcool.a3food.data.Food
import org.d3ifcool.a3food.data.FoodDao

class MainViewModel(private val db : FoodDao) : ViewModel() {
    val data = db.getData()

    fun insertData(food: Food) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insertData(food)
            }
        }
    }

    fun deleteData(ids: List<String>) {
        val newIds = ids.toList()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.deleteData(newIds)
            }
        }
    }
}