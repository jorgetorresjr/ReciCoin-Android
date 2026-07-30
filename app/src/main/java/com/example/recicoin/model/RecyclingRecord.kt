package com.example.recicoin.model

data class RecyclingRecord(
    val userId: String = "",
    val userName: String = "",

    val collectionPointId: String = "",
    val collectionPointName: String = "",

    val material: String = "",
    val quantityKg: Double = 0.0,
    val recicoinsEarned: Int = 0,

    val timestamp: Long = System.currentTimeMillis()
)