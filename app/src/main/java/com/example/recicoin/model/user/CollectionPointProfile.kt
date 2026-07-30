package com.example.recicoin.model.user

import com.example.recicoin.model.Address
import com.example.recicoin.model.MaterialType

data class CollectionPointProfile(
    val description: String,
    val address: Address,
    val latitude: Double,
    val longitude: Double,
    val phone: String,
    val acceptedMaterials: List<MaterialType> = emptyList()
)