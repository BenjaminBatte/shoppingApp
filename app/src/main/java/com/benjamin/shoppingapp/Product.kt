package com.benjamin.shoppingapp

import java.io.Serializable

data class Product(
    val productName: String,
    val productDescription: String,
    val cost: Double
) :
    Serializable {
    var position: Int = -1
}

