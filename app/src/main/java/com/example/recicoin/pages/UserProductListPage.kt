package com.example.recicoin.pages

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.recicoin.repository.ProductRepository
import com.example.recicoin.ui.components.ProductCard
import com.google.firebase.firestore.DocumentSnapshot


@Composable
fun UserProductListPage(
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current

    var search by remember {
        mutableStateOf("")
    }

    val repository = remember {
        ProductRepository()
    }

    var products by remember {
        mutableStateOf<List<DocumentSnapshot>>(emptyList())
    }


    LaunchedEffect(Unit) {

        repository.getAllProducts(
            onSuccess = {
                products = it
            },
            onFailure = { exception ->
                Toast.makeText(
                    context,
                    exception.message ?: "Erro ao carregar produtos",
                    Toast.LENGTH_LONG
                ).show()

            }
        )
    }


    val filteredProducts = products.filter {
        val name = it.getString("name") ?: ""

        name.contains(
            search,
            ignoreCase = true
        )
    }


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Use seus ReciCoins para resgatar produtos",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(
            Modifier.height(7.dp)
        )

        OutlinedTextField(
            value = search,
            onValueChange = {
                search = it
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            label = {
                Text("Pesquisar")
            },
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = null
                )
            },
            singleLine = true
        )

        Spacer(
            Modifier.height(8.dp)
        )

        Text(
            text = "${filteredProducts.size} produtos",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(
            Modifier.height(8.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(
                bottom = 16.dp
            )
        ) {
            items(filteredProducts) { product ->
                ProductCard(
                    product = product,
                    showRedeemButton = true,
                    onRedeemClick = {

                    }
                )
            }
        }
    }
}