package com.example.recicoin.pages

import android.app.Activity
import android.content.Intent
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recicoin.activities.HomeActivity

@Preview(showBackground = true)
@Composable
fun CollectionPointHomePage(modifier: Modifier = Modifier) {
    val activity = LocalActivity.current as HomeActivity

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = { activity.logout() }
            ) {
                Text("Logout")
            }
        }

        Text(
            text = "Bem-vindo(a) ao ReciCoin!",
            fontSize = 22.sp
        )

        Text(
            text = "Cadastre seus produtos e apoie a reciclagem!"
        )
    }
}