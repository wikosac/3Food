package org.d3ifcool.a3food.data

import androidx.lifecycle.LiveData

interface KeyDao {

    fun insertData(key: Key)

    fun getData(): LiveData<List<Key>>

    fun deleteData(ids: List<String>)
}