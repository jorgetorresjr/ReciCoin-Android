package com.example.recicoin.model.user

data class User(
    val id: String = "",
    val name: String,
    val email: String,
    val type: UserType
)