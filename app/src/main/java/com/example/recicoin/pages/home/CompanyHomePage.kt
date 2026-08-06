package com.example.recicoin.pages.home

import android.widget.Toast
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.sp
import com.example.recicoin.activities.HomeActivity
import com.example.recicoin.repository.ProductRepository
import com.example.recicoin.ui.components.ProductCard
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.DocumentSnapshot

@Composable
fun CompanyHomePage(
    modifier: Modifier = Modifier,
    onRegisterClick: () -> Unit
) {
    val activity = LocalActivity.current as HomeActivity
    val uid = Firebase.auth.currentUser!!.uid
    val repository = remember {
        ProductRepository()
    }
    var products by remember {
        mutableStateOf<List<DocumentSnapshot>>(emptyList())
    }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        repository.getProductsByCompany(
            companyId = uid,
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
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Bem-vindo(a) ao ReciCoin!",
            fontSize = 22.sp
        )
        Text(
            text = "Cadastre seus produtos e apoie a reciclagem!",
            fontSize = 16.sp
        )

        Button(
            onClick = onRegisterClick
        ) {
            Text("Cadastrar produto")
        }

        if(products.isEmpty()) {
            Text(
                text = "Nenhum produto cadastrado."
            )

        } else {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(products) { product ->
                    ProductCard(product = product)
                }
            }

        }

    }
}
