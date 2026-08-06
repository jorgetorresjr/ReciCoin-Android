package com.example.recicoin.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class UserProfileViewModel : ViewModel() {
    private val db = Firebase.firestore

    var name = mutableStateOf("")
        private set

    var email = mutableStateOf("")
        private set

    var address = mutableStateOf("")
        private set

    var recicoins = mutableStateOf(0)
        private set

    init {
        loadProfile()
    }

    private fun loadProfile() {
        val uid = Firebase.auth.currentUser?.uid ?: return

        db.collection("users")
            .document(uid)
            .get()
            .addOnSuccessListener { document ->

                Log.d("PROFILE", "Documento existe: ${document.exists()}")
                Log.d("PROFILE", "Dados: ${document.data}")

                name.value = document.getString("name") ?: ""
                email.value = document.getString("email") ?: ""
                recicoins.value = document.getLong("recicoins")?.toInt() ?: 0

                val profile =
                    document.get("profile") as? Map<*, *>

                val addressMap =
                    profile?.get("address") as? Map<*, *>

                address.value = buildString {
                    append(addressMap?.get("street") ?: "")
                    append(", ")
                    append(addressMap?.get("number") ?: "")
                }

                Log.d("PROFILE", "Nome: ${name.value}")
                Log.d("PROFILE", "Email: ${email.value}")
                Log.d("PROFILE", "Endereço: ${address.value}")
                Log.d("PROFILE", "ReciCoins: ${recicoins.value}")
            }
            .addOnFailureListener {
                Log.e("PROFILE", "Erro ao carregar perfil", it)
            }
    }
}