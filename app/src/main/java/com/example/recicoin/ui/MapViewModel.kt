package com.example.recicoin.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.recicoin.model.Address
import com.example.recicoin.model.user.CollectionPointProfile
import com.example.recicoin.model.user.User
import com.example.recicoin.model.user.UserType
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

data class MapCollectionPoint(
    val uid: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val address: Address,
    val phone: String,
    val description: String
)

class MapViewModel : ViewModel() {
    private val db = Firebase.firestore
    var collectionPoints by mutableStateOf<List<MapCollectionPoint>>(emptyList())
        private set

    init {
        loadCollectionPoints()
    }

    private fun loadCollectionPoints() {
        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                collectionPoints = result.documents.mapNotNull { document ->

                    val type = document.getString("type")
                    if (type != UserType.COLLECTION_POINT.name)
                        return@mapNotNull null

                    val name = document.getString("name") ?: ""
                    val uid = document.getString("uid") ?: document.id
                    val profile = document.get("profile") as? Map<*, *>
                            ?: return@mapNotNull null

                    val description = profile["description"] as? String ?: ""
                    val phone = profile["phone"] as? String ?: ""
                    val latitude = (profile["latitude"] as? Number)?.toDouble()
                            ?: return@mapNotNull null
                    val longitude = (profile["longitude"] as? Number)?.toDouble()
                            ?: return@mapNotNull null
                    val addressMap = profile["address"] as? Map<*, *>
                            ?: return@mapNotNull null

                    val address = Address(
                        street = addressMap["street"] as? String ?: "",
                        number = addressMap["number"] as? String ?: "",
                        neighborhood = addressMap["neighborhood"] as? String ?: "",
                        city = addressMap["city"] as? String ?: "",
                        state = addressMap["state"] as? String ?: "",
                        zipCode = addressMap["zipCode"] as? String ?: ""
                    )

                    MapCollectionPoint(
                        uid = uid,
                        name = name,
                        latitude = latitude,
                        longitude = longitude,
                        address = address,
                        phone = phone,
                        description = description
                    )
                }
            }
    }

}