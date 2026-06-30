package com.example.recicoin.pages.register

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recicoin.activities.LoginActivity
import com.example.recicoin.model.Address
import com.example.recicoin.model.user.CollectionPointProfile
import com.example.recicoin.model.user.User
import com.example.recicoin.model.user.UserType
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

@Preview(showBackground = true)
@Composable
fun RegisterCollectionPointPage(modifier: Modifier = Modifier) {
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var repeatPassword by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var street by rememberSaveable { mutableStateOf("") }
    var number by rememberSaveable { mutableStateOf("") }
    var neighborhood by rememberSaveable { mutableStateOf("") }
    var city by rememberSaveable { mutableStateOf("") }
    var state by rememberSaveable { mutableStateOf("") }
    var zipCode by rememberSaveable { mutableStateOf("") }
    val activity = LocalActivity.current as Activity


    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 46.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(17.dp)
    ) {
        Text(
            text = "Criar conta",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            value = name,
            label = { Text("Digite seu nome") },
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
            onValueChange = { name = it }
        )

        OutlinedTextField(
            value = email,
            label = { Text("Digite seu e-mail") },
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { email = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )
        )

        OutlinedTextField(
            value = password,
            label = { Text("Digite sua senha") },
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { password = it },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            )
        )

        OutlinedTextField(
            value = repeatPassword,
            label = { Text("Confirme sua senha") },
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { repeatPassword = it },
            visualTransformation = PasswordVisualTransformation()
        )
        OutlinedTextField(
            value = phone,
            label = { Text("Digite o número de telefone") },
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
            onValueChange = { phone = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            )
        )
        OutlinedTextField(
            value = description,
            label = { Text("Descreva o que é coletado") },
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
            onValueChange = { description = it }
        )

        Spacer(modifier = Modifier.size(7.dp))

        Text(
            text = "Endereço",
            fontSize = 24.sp
        )

        OutlinedTextField(
            value = street,
            label = { Text("Logradouro") },
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
            onValueChange = { street = it }
        )
        OutlinedTextField(
            value = number,
            label = { Text("Número do endereço") },
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
            onValueChange = { number = it }
        )
        OutlinedTextField(
            value = neighborhood,
            label = { Text("Bairro") },
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
            onValueChange = { neighborhood  = it }
        )
        OutlinedTextField(
            value = city,
            label = { Text("Cidade") },
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
            onValueChange = { city = it }
        )
        OutlinedTextField(
            value = state,
            label = { Text("Estado") },
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
            onValueChange = { state = it }
        )
        OutlinedTextField(
            value = zipCode,
            label = { Text("CEP") },
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
            onValueChange = { zipCode = it }
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                12.dp,
                alignment = CenterHorizontally
            ),
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp)
        ) {
            Button(
                onClick = {
                    val address = Address(
                        street = street,
                        number = number,
                        neighborhood = neighborhood,
                        city = city,
                        state = state,
                        zipCode = zipCode
                    )
                    Firebase.auth
                        .createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(activity) { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(
                                    activity,
                                    "Collection Point created!",
                                    Toast.LENGTH_LONG
                                ).show()

                                val uid = Firebase.auth.currentUser!!.uid

                                val user = User(
                                    uid = uid,
                                    name = name,
                                    email = email,
                                    type = UserType.COLLECTION_POINT
                                )

                                val profile = CollectionPointProfile(
                                    description = description,
                                    address = address,
                                    latitude = 0.0,
                                    longitude = 0.0,
                                    phone = phone
                                )

                                val data = hashMapOf(
                                    "uid" to user.uid,
                                    "name" to user.name,
                                    "email" to user.email,
                                    "type" to user.type.name,
                                    "profile" to profile
                                )


                                Firebase.firestore
                                    .collection("users")
                                    .document(uid)
                                    .set(data)
                                    .addOnSuccessListener {

                                        Toast.makeText(
                                            activity,
                                            "Register OK!",
                                            Toast.LENGTH_LONG
                                        ).show()

                                        activity.startActivity(
                                            Intent(activity, LoginActivity::class.java)
                                        )

                                        activity.finish()

                                    }
                                    .addOnFailureListener {
                                        Firebase.auth.currentUser?.delete()

                                        Toast.makeText(
                                            activity,
                                            "Register Error",
                                            Toast.LENGTH_LONG
                                        ).show()

                                    }
                            }
                            else {
                                Toast.makeText(
                                    activity,
                                    task.exception?.message ?: "Error",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                },
                enabled = name.isNotEmpty() &&
                        email.isNotEmpty() &&
                        password.isNotEmpty() &&
                        repeatPassword.isNotEmpty() &&
                        password == repeatPassword
            ) {
                Text("Registrar")
            }

            Button(
                onClick = {
                    name = ""
                    email = ""
                    password = ""
                    repeatPassword = ""
                    phone = ""
                    description = ""
                    street = ""
                    number = ""
                    neighborhood = ""
                    city = ""
                    state = ""
                    zipCode = ""
                }
            ) {
                Text("Limpar")
            }
        }
    }
}
