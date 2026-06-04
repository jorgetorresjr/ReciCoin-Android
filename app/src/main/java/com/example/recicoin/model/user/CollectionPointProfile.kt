package com.example.recicoin.model.user

import com.example.recicoin.model.Address

data class CollectionPointProfile(
    val description: String,
    val address: Address,
    val latitude: Double,
    val longitude: Double,
    val phone: String
)