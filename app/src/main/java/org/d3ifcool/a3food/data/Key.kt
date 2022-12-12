package org.d3ifcool.a3food.data

import com.google.firebase.database.Exclude

data class Key(
    @get:Exclude
    var key: String,
    var value: String
)
