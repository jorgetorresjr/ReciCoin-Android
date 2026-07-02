package com.example.recicoin.pages

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.recicoin.repository.CollectionPointRepository
import com.google.firebase.firestore.DocumentSnapshot

@Composable
fun CollectionPointListPage(padding: Modifier) {
    val repository = remember {
        CollectionPointRepository()
    }

    var points by remember {
        mutableStateOf<List<DocumentSnapshot>>(emptyList())
    }

    LaunchedEffect(Unit) {
        repository.getAllCollectionPoints(
            onSuccess = {
                points = it
            },
            onFailure = {

            }
        )

    }

    LazyColumn {
        items(points) { point ->
            Text(
                text = point.getString("name") ?: ""
            )

        }

    }

}