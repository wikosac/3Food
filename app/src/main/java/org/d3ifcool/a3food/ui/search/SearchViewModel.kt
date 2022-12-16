package org.d3ifcool.a3food.ui.search

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3ifcool.a3food.data.Food
import org.d3ifcool.a3food.data.Key
import org.d3ifcool.a3food.data.KeyDao
import org.d3ifcool.a3food.databinding.FragmentSearchBinding

class SearchViewModel(private val db : KeyDao) : ViewModel() {

    val data = db.getData()
    private lateinit var binding: FragmentSearchBinding

    fun insertData(key: Key) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insertData(key)
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

    fun validation(key: String) {
        // Pengecekan kalo inputnya kosong
        if (binding.searchView.text?.isEmpty() == true) {
            showMessage("Inputan gaboleh kosong")
            return
        }

        // kalo inputan berupa angka (tapi lu pake string / text)
        if (binding.searchView.text?.isDigitsOnly() == true) {
            showMessage("Inputan gabisa angka")
            return
        }

        // kalo inputan simbol
        if (binding.searchView.text?.contains("[!\"#$%&'()*+,-./:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex()) == true) {
            showMessage("Inputan gabisa simbol")
            return
        }
    }

    fun showMessage(msg: String) {
        binding.testOutput.text = msg
    }
}