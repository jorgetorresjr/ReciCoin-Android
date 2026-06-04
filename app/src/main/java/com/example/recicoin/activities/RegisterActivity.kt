package com.example.recicoin.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recicoin.model.user.UserType
import com.example.recicoin.pages.register.RegisterCollectionPointPage
import com.example.recicoin.pages.register.RegisterCompanyPage
import com.example.recicoin.pages.register.RegisterUserPage
import com.example.recicoin.ui.theme.ReciCoinTheme

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val userTypeString = intent.getStringExtra("userType")
        val userType = try {
            UserType.valueOf(userTypeString ?: "USER")
        } catch (e: Exception) {
            UserType.USER
        }

        setContent {
            ReciCoinTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    when (userType) {

                        UserType.USER -> {
                            RegisterUserPage(
                                modifier = Modifier.padding(innerPadding)
                            )
                        }

                        UserType.COMPANY -> {
                            RegisterCompanyPage(
                                modifier = Modifier.padding(innerPadding)
                            )
                        }

                        UserType.COLLECTION_POINT -> {
                            RegisterCollectionPointPage(
                                modifier = Modifier.padding(innerPadding)
                            )
                        }

                        UserType.ADMIN -> {

                        }
                    }
                }
            }
        }
    }
}
