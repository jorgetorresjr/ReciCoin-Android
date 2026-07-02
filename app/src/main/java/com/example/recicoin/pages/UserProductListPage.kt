package com.example.recicoin.pages

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.recicoin.repository.ProductRepository
import com.example.recicoin.ui.ProductCard
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
            onFailure = {
                exception ->
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
        modifier = modifier.fillMaxSize()
    ) {

        OutlinedTextField(
            value = search,
            onValueChange = {
                search = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            label = {
                Text("Pesquisar produto")
            },
            leadingIcon = {
                Icon(Icons.Default.Search, null)
            }
        )

        LazyColumn {
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