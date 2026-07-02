package com.example.recicoin.pages.register

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recicoin.model.Product
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

@Composable
fun RegisterProductPage(
    modifier: Modifier = Modifier,
    onProductRegistered: () -> Unit
) {
    var name by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var price by rememberSaveable { mutableStateOf("") }
    var stock by rememberSaveable { mutableStateOf("") }

    val activity = LocalActivity.current as Activity

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Cadastrar Produto",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Nome do produto") }
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Descrição") }
        )

        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Preço (ReciCoins)") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        OutlinedTextField(
            value = stock,
            onValueChange = { stock = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Estoque") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )

        Button(
            onClick = {
                val uid = Firebase.auth.currentUser!!.uid

                Firebase.firestore
                    .collection("users")
                    .document(uid)
                    .get()
                    .addOnSuccessListener { document ->

                        val companyName = document.getString("name") ?: ""
                        val product = Product(
                            name = name,
                            description = description,
                            price = price.toInt(),
                            stock = stock.toInt(),
                            companyId = uid,
                            companyName = companyName
                        )

                        Firebase.firestore
                            .collection("products")
                            .add(product)
                            .addOnSuccessListener {
                                Toast.makeText(
                                    activity,
                                    "Produto cadastrado!",
                                    Toast.LENGTH_LONG
                                ).show()

                                name = ""
                                description = ""
                                price = ""
                                stock = ""

                                onProductRegistered()

                            }
                            .addOnFailureListener {

                                Toast.makeText(
                                    activity,
                                    "Erro ao cadastrar produto.",
                                    Toast.LENGTH_LONG
                                ).show()

                            }

                    }

            },
            enabled = name.isNotBlank() &&
                        description.isNotBlank() &&
                        price.isNotBlank() &&
                        stock.isNotBlank()
        ) {
            Text("Cadastrar")
        }

    }
}