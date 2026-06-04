package com.example.recicoin.pages.register

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recicoin.activities.LoginActivity

@Preview(showBackground = true)
@Composable
fun RegisterCompanyPage(modifier: Modifier = Modifier) {
    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var repeatPassword by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
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
            text = "Criar conta de empresa",
            fontSize = 24.sp
        )
        OutlinedTextField(
            value = name,
            label = { Text("Digite o nome da empresa") },
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
            onValueChange = { name = it }
        )

        OutlinedTextField(
            value = email,
            label = { Text("Digite o e-mail") },
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
            label = { Text("Descreva a empresa") },
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
            onValueChange = { description = it }
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
                    activity.startActivity(
                        Intent(activity, LoginActivity::class.java)
                    )
                    Toast.makeText(activity, "Registro Empresa OK!", Toast.LENGTH_LONG).show()
                    activity.finish()
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
                }
            ) {
                Text("Limpar")
            }
        }
    }
}

