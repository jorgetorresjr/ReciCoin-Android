package com.example.recicoin.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun ProfileInfoItem(
    icon: ImageVector,
    title: String,
    value: String
) {
    ListItem(
        leadingContent = {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        },
        headlineContent = {
            Text(title)
        },
        supportingContent = {
            Text(value)
        }
    )
}