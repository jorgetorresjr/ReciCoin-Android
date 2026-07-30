package com.example.recicoin.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class UserHomeViewModel : ViewModel() {

    private val db = Firebase.firestore

    var recicoins = mutableStateOf(0)


    init {
        loadCoins()
    }


    private fun loadCoins() {

        val uid = Firebase.auth.currentUser?.uid
            ?: return


        db.collection("users")
            .document(uid)
            .get()
            .addOnSuccessListener { document ->

                recicoins.value =
                    document.getLong("recicoins")?.toInt() ?: 0

            }

    }

}