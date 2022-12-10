package org.d3ifcool.a3food.data

import androidx.lifecycle.LiveData
import com.google.firebase.database.FirebaseDatabase

class FoodDb private constructor() {

    private val database = FirebaseDatabase.getInstance().getReference(PATH)

    val dao = object : FoodDao {
        override fun insertData(food: Food) {
            database.push().setValue(food)
        }
        override fun getData(): LiveData<List<Food>> {
            return FoodLiveData(database)
        }
        override fun deleteData(ids: List<String>) {
            ids.forEach { database.child(it).removeValue() }
        }
    }

    companion object {
        private const val PATH = "food"
        @Volatile
        private var INSTANCE: FoodDb? = null
        fun getInstance(): FoodDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = FoodDb()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}