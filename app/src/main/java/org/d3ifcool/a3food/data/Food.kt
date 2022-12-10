package org.d3ifcool.a3food.data

import com.google.firebase.database.Exclude

data class Food(
    @get:Exclude
    var id: String,
    var nama: String,
    var toko: String,
    var harga: String
)
