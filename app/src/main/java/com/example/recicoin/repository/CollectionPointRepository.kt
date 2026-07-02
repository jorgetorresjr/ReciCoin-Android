package com.example.recicoin.repository

import com.example.recicoin.model.user.UserType
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.firestore

class CollectionPointRepository {
    private val db = Firebase.firestore

    fun getAllCollectionPoints(
        onSuccess: (List<DocumentSnapshot>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {

        db.collection("users")
            .whereEqualTo(
                "type",
                UserType.COLLECTION_POINT.name
            )
            .get()
            .addOnSuccessListener {
                onSuccess(it.documents)
            }
            .addOnFailureListener {
                onFailure(it)
            }

    }
}