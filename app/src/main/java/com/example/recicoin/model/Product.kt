package com.example.recicoin.model

data class Product(
    val name: String = "",
    val description: String = "",
    val price: Int = 0,
    val stock: Int = 0,
    val companyId: String = "",
    val companyName: String = "",
    val imageUrl: String? = null
)