package com.example.recicoin.model.user

import com.example.recicoin.model.Address

data class UserProfile(
    val address: Address,
    var recicoins: Int = 0
)