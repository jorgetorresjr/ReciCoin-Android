package com.example.recicoin.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.recicoin.model.MaterialType
import com.google.firebase.firestore.FirebaseFirestore

class RegisterMaterialViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    var material by mutableStateOf(MaterialType.PAPER)
    var weight by mutableStateOf("")

    fun calculateCoins(): Int {

        val kg = weight.toIntOrNull() ?: 0

        return kg * material.coinsPerKg
    }
    fun registerRecycle(
        userId: String
    ) {
        val coins = calculateCoins()

        if(coins <= 0)
            return


        val userRef = db
            .collection("users")
            .document(userId)

        db.runTransaction { transaction ->
            val snapshot = transaction.get(userRef)

            val currentCoins =
                snapshot.getLong("recicoins") ?: 0

            transaction.update(
                userRef,
                "recicoins",
                currentCoins + coins
            )

        }

    }

}