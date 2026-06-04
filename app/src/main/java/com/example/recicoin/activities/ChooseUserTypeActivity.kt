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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
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
        modifier = Modifier.fillMaxWidth().padding(top = 58.dp),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(46.dp)
    ) {
        Text(
            "Eu sou...",
            fontSize = 24.sp
        )

        Button(
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
            Column (horizontalAlignment = CenterHorizontally){
                Text(
                    "Usuário Reciclador",
                    fontSize = 18.sp
                )
                Text(
                    "Recicle e receba recicoins para trocar por produtos",
                    fontSize = 10.sp
                )
            }
        }

        Button(
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
            Column (horizontalAlignment = CenterHorizontally){
                Text(
                    "Ponto de Coleta",
                    fontSize = 18.sp
                )
                Text(
                    "Se você quer ser um ponto de reciclagem",
                    fontSize = 10.sp
                )
            }
        }

        Button(
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
            Column (horizontalAlignment = CenterHorizontally){
                Text(
                    "Empresa",
                    fontSize = 18.sp
                )
                Text(
                    "Ofereça recompensas em troca de recicoins",
                    fontSize = 10.sp
                )
            }
        }
    }
}