package com.example.recicoin.repository

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.DocumentSnapshot

class ProductRepository {

    fun getProductsByCompany(
        companyId: String,
        onSuccess: (List<DocumentSnapshot>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        Firebase.firestore
            .collection("products")
            .whereEqualTo("companyId", companyId)
            .get()
            .addOnSuccessListener {
                onSuccess(it.documents)
            }
            .addOnFailureListener {
                onFailure(it)
            }
    }
    fun getAllProducts(
        onSuccess: (List<DocumentSnapshot>) -> Unit,
        onFailure: (Exception) -> Unit
    ) {

        Firebase.firestore
            .collection("products")
            .get()
            .addOnSuccessListener { documents ->
                onSuccess(documents.documents)
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }

    }

}