package com.example.recicoin.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.example.recicoin.model.user.UserType
import com.example.recicoin.pages.main.CollectionPointMainPage
import com.example.recicoin.pages.main.CompanyMainPage
import com.example.recicoin.pages.main.UserMainPage
import com.example.recicoin.ui.theme.ReciCoinTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        val userTypeString = intent.getStringExtra("userType")

        val userType = try {
            UserType.valueOf(userTypeString!!)
        } catch (e: Exception) {
            finish()
            return
        }
        setContent {
            ReciCoinTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    when(userType) {
                        UserType.USER -> {
                            UserMainPage(
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                        UserType.COMPANY -> {
                            CompanyMainPage(
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                        UserType.COLLECTION_POINT -> {
                            CollectionPointMainPage(
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                        UserType.ADMIN -> {
                            Text("Admin Home")
                        }
                    }
                }
            }
        }
    }

    fun logout() {

        Firebase.auth.signOut()

        val intent = Intent(
            this,
            LoginActivity::class.java
        )

        intent.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK

        startActivity(intent)
        finish()
    }
}
