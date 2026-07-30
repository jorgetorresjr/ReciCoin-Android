package com.example.recicoin.pages.profile

import androidx.activity.compose.LocalActivity
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.recicoin.activities.HomeActivity

@Composable
fun UserProfilePage(padding: Modifier) {
    val activity = LocalActivity.current as HomeActivity

    Button(
        onClick = { activity.logout() }
    ) {
        Text("Logout")
    }
}