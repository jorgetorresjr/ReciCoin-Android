package com.example.recicoin.activities

import android.app.Activity
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recicoin.model.user.UserType
import com.example.recicoin.ui.theme.ReciCoinTheme
import kotlin.jvm.java

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ReciCoinTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    LoginPage(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPage(modifier: Modifier = Modifier) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val activity = LocalActivity.current as Activity

    Column(
        modifier = modifier.padding(24.dp).fillMaxSize(),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val modifier = modifier.fillMaxWidth(fraction = 0.9f)
        Text(
            text = "Bem-vindo(a)!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            value = email,
            label = { Text(text = "Digite seu e-mail") },
            modifier = modifier,
            onValueChange = { email = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            )
        )
        OutlinedTextField(
            value = password,
            label = { Text(text = "Digite sua senha") },
            modifier = modifier,
            onValueChange = { password = it },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            )
        )
        Row (horizontalArrangement = Arrangement.Center){
            Button(onClick = {
                Toast.makeText(
                    activity,
                    "Login OK!",
                    Toast.LENGTH_LONG
                ).show()

                val intent = Intent(
                    activity,
                    HomeActivity::class.java
                )

                intent.putExtra(
                    "userType",
                    UserType.USER.name
                )

                activity.startActivity(intent)
            },

                enabled = email.isNotEmpty() &&
                        password.isNotEmpty()
            ) {
                Text("Login",
                    fontSize = 10.sp)
            }

            Spacer(modifier = Modifier.size(11.dp))

            Button( onClick = {
                activity.startActivity(
                    Intent(activity, ChooseUserTypeActivity::class.java).setFlags(
                        FLAG_ACTIVITY_SINGLE_TOP
                    )
                )
            }
            ) {
                Text("Registrar",
                    fontSize = 10.sp)
            }

            Spacer(modifier = Modifier.size(11.dp))

            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF7A7979),
                    contentColor = Color.White
                ),
                onClick = { email = ""; password = "" }
            ) {
                Text("Limpar",
                    fontSize = 10.sp)
            }
        }
    }
}