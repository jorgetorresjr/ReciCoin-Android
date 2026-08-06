package com.example.recicoin.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
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
fun CollectionPointListPage(
    modifier: Modifier = Modifier
) {
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

        val profile = point.get("profile") as? Map<*, *>
                ?: emptyMap<Any, Any>()

        val address = profile["address"] as? Map<*, *>
                ?: emptyMap<Any, Any>()
        val street = address["street"]?.toString() ?: ""

        name.contains(search, true) ||
                street.contains(search, true)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Spacer(
            Modifier.height(12.dp)
        )

        Text(
            text = "Pontos de coleta",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Encontre locais para descartar seus recicláveis",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(
            Modifier.height(12.dp)
        )

        OutlinedTextField(
            value = search,
            onValueChange = {
                search = it
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            label = {
                Text("Pesquisar")
            },
            placeholder = {
                Text("Nome ou endereço")
            },
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = null
                )
            }
        )

        Spacer(
            Modifier.height(8.dp)
        )

        Text(
            text = "${filteredPoints.size} pontos encontrados",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(
            Modifier.height(8.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(
                bottom = 16.dp
            )
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
    val profile = point.get("profile") as? Map<*, *>
            ?: emptyMap<Any, Any>()

    val address = profile["address"] as? Map<*, *>
            ?: emptyMap<Any, Any>()

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSurface,
            contentColor = MaterialTheme.colorScheme.surface,
        ), onClick = {

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

            Spacer(
                Modifier.height(8.dp)
            )

            Row {
                Icon(
                    Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.surface
                )

                Spacer(
                    Modifier.width(8.dp)
                )

                Text(
                    text = "${address["street"]}, ${address["number"]}",
                    color = MaterialTheme.colorScheme.surface
                )
            }

            Spacer(
                Modifier.height(6.dp)
            )

            Row {
                Icon(
                    Icons.Default.Phone,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.surface
                )

                Spacer(
                    Modifier.width(8.dp)
                )

                Text(
                    profile["phone"]?.toString() ?: ""
                )
            }

            Spacer(
                Modifier.height(8.dp)
            )

            AssistChip(
                onClick = {},
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    labelColor = MaterialTheme.colorScheme.onSurface
                ),
                label = {
                    Text(
                        profile["description"]?.toString()
                            ?: "Materiais recicláveis"
                    )
                }
            )
        }
    }
}