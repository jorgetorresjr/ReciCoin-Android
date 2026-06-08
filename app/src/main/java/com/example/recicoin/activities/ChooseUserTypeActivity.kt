package com.example.recicoin.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recicoin.model.user.UserType
import com.example.recicoin.ui.theme.ReciCoinTheme

class ChooseUserTypeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ReciCoinTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    ChooseUserTypePage(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChooseUserTypePage(modifier: Modifier = Modifier) {
    val activity = LocalActivity.current as Activity

    Column(
        modifier = modifier.fillMaxSize().padding(top = 58.dp),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(46.dp)
    ) {
        Text(
            "Como deseja usar o ReciCoin?",
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold
        )

        Button(
            modifier = Modifier.fillMaxWidth(0.9f),
            contentPadding = PaddingValues(vertical = 18.dp),
            shape = RoundedCornerShape(28.dp),
            onClick = {

                val intent = Intent(
                    activity,
                    RegisterActivity::class.java
                )

                intent.putExtra(
                    "userType",
                    UserType.USER.name
                )

                activity.startActivity(intent)
            }
        ) {
            Column (
                horizontalAlignment = CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ){
                Text(
                    "Reciclador",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    "Recicle e receba recicoins para trocar por produtos",
                    fontSize = 11.sp
                )
            }
        }

        Button(
            modifier = Modifier.fillMaxWidth(0.9f),
            contentPadding = PaddingValues(vertical = 18.dp),
            shape = RoundedCornerShape(28.dp),
            onClick = {

                val intent = Intent(
                    activity,
                    RegisterActivity::class.java
                )

                intent.putExtra(
                    "userType",
                    UserType.COLLECTION_POINT.name
                )

                activity.startActivity(intent)
            }
        ) {
            Column (
                horizontalAlignment = CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ){
                Text(
                    "Ponto de Coleta",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    "Se você quer ser um ponto de reciclagem",
                    fontSize = 11.sp
                )
            }
        }

        Button(
            modifier = Modifier.fillMaxWidth(0.9f),
            contentPadding = PaddingValues(vertical = 18.dp),
            shape = RoundedCornerShape(28.dp),
            onClick = {
                val intent = Intent(
                    activity,
                    RegisterActivity::class.java
                )
                intent.putExtra(
                    "userType",
                    UserType.COMPANY.name
                )

                activity.startActivity(intent)
            }
        ) {
            Column (
                horizontalAlignment = CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ){
                Text(
                    "Empresa",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    "Ofereça recompensas em troca de recicoins",
                    fontSize = 11.sp
                )
            }
        }

    }
}