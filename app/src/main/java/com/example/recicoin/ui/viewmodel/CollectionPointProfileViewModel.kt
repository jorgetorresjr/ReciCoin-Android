package com.example.recicoin.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class CollectionPointProfileViewModel : ViewModel() {
    private val db = Firebase.firestore

    var name = mutableStateOf("")
        private set

    var email = mutableStateOf("")
        private set

    var phone = mutableStateOf("")
        private set

    var address = mutableStateOf("")
        private set

    var description = mutableStateOf("")
        private set

    var acceptedMaterials = mutableStateOf("")
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

                val addressMap = profile?.get("address") as? Map<*, *>

                address.value = buildString {
                    append(addressMap?.get("street") ?: "")
                    append(", ")
                    append(addressMap?.get("number") ?: "")

                    append(" - ")
                    append(addressMap?.get("neighborhood") ?: "")

                    append(", ")
                    append(addressMap?.get("city") ?: "")
                }

                val materials = profile?.get("acceptedMaterials") as? List<*>

                acceptedMaterials.value = materials?.joinToString(", ") ?: ""
            }
    }
}