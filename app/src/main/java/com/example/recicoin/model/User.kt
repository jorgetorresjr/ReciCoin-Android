package com.example.recicoin.model

data class User (
    val name: String,
    val email: String,
    val type: UserType,
    val address: Address,
    var recicoins: Int = 0
)