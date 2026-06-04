package com.example.recicoin.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun UserHomePage(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 46.dp),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(17.dp)
    ) {
        Text(
            text = "Bem-vindo(a) ao ReciCoin!",
            fontSize = 22.sp
        )
        Text(
            text = "Recicle já no ponto de coleta mais próximo",
            fontSize = 16.sp
        )

    }
}