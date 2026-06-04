package com.example.recicoin.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.recicoin.model.user.UserType
import com.example.recicoin.pages.CollectionPointHomePage
import com.example.recicoin.pages.CompanyHomePage
import com.example.recicoin.pages.UserHomePage
import com.example.recicoin.ui.theme.ReciCoinTheme

class HomeActivity : ComponentActivity() {

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
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    when(userType) {
                        UserType.USER -> {
                            UserHomePage(
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                        UserType.COMPANY -> {
                            CompanyHomePage(
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                        UserType.COLLECTION_POINT -> {
                            CollectionPointHomePage(
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
