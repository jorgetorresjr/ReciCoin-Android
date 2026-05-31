package com.example.recicoin.model

data class Product(
    val name: String,
    val description: String,
    val price: Int,
    var stock: Int,
    val companyName: String,
    val imageUrl: String? = null
)