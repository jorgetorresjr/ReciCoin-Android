package com.example.recicoin.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class CollectionRegisterViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    var search = mutableStateOf("")
    var userFound = mutableStateOf<DocumentSnapshot?>(null)
    var error = mutableStateOf("")

    fun searchUser() {
        val value = search.value.trim()

        if(value.isEmpty()) {
            error.value = "Digite um CPF ou email"
            return
        }


        db.collection("users")
            .whereEqualTo("cpf", value)
            .get()
            .addOnSuccessListener { result ->
                if(!result.isEmpty) {
                    val document = result.documents.first()

                    userFound.value = result.documents.first()
                } else {
                    db.collection("users")
                        .whereEqualTo("email", value)
                        .get()
                        .addOnSuccessListener { emailResult ->

                            if(!emailResult.isEmpty) {
                                userFound.value = emailResult.documents.first()

                            } else {

                                userFound.value = null
                                error.value = "Usuário não encontrado"

                            }

                        }

                }

            }

    }
}