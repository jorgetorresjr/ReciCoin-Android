package com.example.recicoin.model.user

data class User(
    val uid: String = "",
    val name: String,
    val email: String,
    val type: UserType
)