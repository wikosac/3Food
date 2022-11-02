package org.d3ifcool.a3food

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val authState = FirebaseUserLiveData()
}