package org.d3ifcool.a3food.data

import androidx.lifecycle.LiveData

interface FoodDao {

    fun insertData(food: Food)

    fun getData(): LiveData<List<Food>>

    fun deleteData(ids: List<String>)
}