package com.example.recicoin.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class CompanyProfileViewModel : ViewModel() {
    private val db = Firebase.firestore

    var name = mutableStateOf("")
        private set

    var email = mutableStateOf("")
        private set

    var phone = mutableStateOf("")
        private set

    var description = mutableStateOf("")
        private set

    var logoUrl = mutableStateOf<String?>(null)
        private set

    init {
        loadProfile()
    }

    private fun loadProfile() {
        val uid = Firebase.auth.currentUser?.uid
            ?: return

        db.collection("users")
            .document(uid)
            .get()
            .addOnSuccessListener { document ->
                name.value = document.getString("name") ?: ""
                email.value = document.getString("email") ?: ""

                val profile = document.get("profile") as? Map<*, *>

                phone.value = profile?.get("phone") as? String ?: ""
                description.value = profile?.get("description") as? String ?: ""
                logoUrl.value = profile?.get("logoUrl") as? String
            }
    }
}