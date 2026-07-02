package com.example.recicoin.pages.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.recicoin.pages.CollectionPointListPage
import com.example.recicoin.pages.FavoritesPage
import com.example.recicoin.pages.HistoryPage
import com.example.recicoin.pages.home.UserHomePage
import com.example.recicoin.pages.profile.UserProfilePage
import com.example.recicoin.ui.nav.UserBottomBar

@Composable
fun UserMainPage(modifier: Modifier) {
    var selectedTab by remember {
        mutableStateOf(0)
    }

    Scaffold(
        bottomBar = {
            UserBottomBar(
                selectedTab = selectedTab,
                onTabSelected = {
                    selectedTab = it
                }
            )

        }

    ) { padding ->
        when(selectedTab){
            0 -> UserHomePage(
                Modifier.padding(padding)
            )

            1 -> CollectionPointListPage(
                Modifier.padding(padding)
            )

            2 -> FavoritesPage(
                Modifier.padding(padding)
            )

            3 -> HistoryPage(
                Modifier.padding(padding)
            )

            4 -> UserProfilePage(
                Modifier.padding(padding)
            )

        }

    }

}