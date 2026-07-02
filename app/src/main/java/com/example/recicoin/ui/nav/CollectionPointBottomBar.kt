package com.example.recicoin.ui.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.Recycling
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun CollectionPointBottomBar(selectedTab: Int, onTabSelected: (Int) -> Unit) {
    NavigationBar (containerColor = Color.White){
        NavigationBarItem(
            selected = selectedTab == 0,
            onClick = { onTabSelected(0) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF2E7D32),
                selectedTextColor = Color(0xFF2E7D32),
                indicatorColor = Color(0xFFC8E6C9),
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            ),
            icon = {
                Icon(Icons.Default.Home, null)
            },
            label = {
                Text("Home")
            }
        )

        NavigationBarItem(
            selected = selectedTab == 1,
            onClick = { onTabSelected(1) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF2E7D32),
                selectedTextColor = Color(0xFF2E7D32),
                indicatorColor = Color(0xFFC8E6C9),
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            ),
            icon = {
                Icon(Icons.Default.Recycling, null)
            },
            label = {
                Text("Registrar Entrega")
            }
        )

        NavigationBarItem(
            selected = selectedTab == 2,
            onClick = { onTabSelected(2) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF2E7D32),
                selectedTextColor = Color(0xFF2E7D32),
                indicatorColor = Color(0xFFC8E6C9),
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            ),
            icon = {
                Icon(Icons.Default.PermIdentity, null)
            },
            label = {
                Text("Perfil")
            }
        )
    }
}