package com.example.recicoin.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.recicoin.repository.CollectionPointRepository
import com.google.firebase.firestore.DocumentSnapshot

@Composable
fun CollectionPointListPage(modifier: Modifier = Modifier) {
    var search by remember {
        mutableStateOf("")
    }

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

    val filteredPoints = points.filter { point ->

        val name = point.getString("name") ?: ""

        val profile = point.get("profile") as? Map<*, *> ?: emptyMap<Any, Any>()
        val address = profile["address"] as? Map<*, *> ?: emptyMap<Any, Any>()

        val street = address["street"]?.toString() ?: ""

        name.contains(search, ignoreCase = true) ||
                street.contains(search, ignoreCase = true)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {

        OutlinedTextField(
            value = search,
            onValueChange = { search = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true,
            label = {
                Text("Pesquisar ponto de coleta")
            },
            placeholder = {
                Text("Nome ou endereço")
            },
            leadingIcon = {
                Icon(Icons.Default.Search, null)
            }
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(filteredPoints) { point ->
                CollectionPointCard(point)
            }
        }
    }

}

@Composable
fun CollectionPointCard(
    point: DocumentSnapshot
) {

    val profile = point.get("profile") as? Map<*, *> ?: emptyMap<Any, Any>()

    val address = profile["address"] as? Map<*, *> ?: emptyMap<Any, Any>()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSurface,
            contentColor = MaterialTheme.colorScheme.surface
        ),
        onClick = {

        }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = point.getString("name") ?: "",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.background
            )
            Spacer(Modifier.height(8.dp))
            Text(
                "📍 ${
                    address["street"]
                }, ${
                    address["number"]
                }"
            )
            Text(
                "📞 ${
                    profile["phone"]
                }"
            )
            Spacer(Modifier.height(8.dp))

            Text(
                "♻ ${
                    profile["description"]
                }"
            )
        }
    }
}