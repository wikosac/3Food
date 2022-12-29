package org.d3ifcool.a3food.data

import androidx.lifecycle.LiveData
import com.google.firebase.database.FirebaseDatabase

class KeyDb private constructor() {

    private val database = FirebaseDatabase.getInstance().getReference(PATH)

    val dao = object : KeyDao {
        override fun insertData(key: Key) {
            database.push().setValue(key)
        }
        override fun getData(): LiveData<List<Key>> {
            return KeyLiveData(database)
        }
        override fun deleteData(ids: List<String>) {
            ids.forEach { database.child(it).removeValue() }
        }
    }

    companion object {
        private const val PATH = "key"
        @Volatile
        private var INSTANCE: KeyDb? = null
        fun getInstance(): KeyDb {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = KeyDb()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}