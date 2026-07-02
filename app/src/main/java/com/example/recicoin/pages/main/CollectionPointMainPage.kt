package com.example.recicoin.pages.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.recicoin.pages.RecyclingRegisterPage
import com.example.recicoin.pages.home.CollectionPointHomePage
import com.example.recicoin.pages.profile.CollectionPointProfilePage
import com.example.recicoin.ui.nav.CollectionPointBottomBar

@Composable
fun CollectionPointMainPage(modifier: Modifier) {
    var selectedTab by remember {
        mutableStateOf(0)
    }

    Scaffold(
        bottomBar = {
            CollectionPointBottomBar(
                selectedTab = selectedTab,
                onTabSelected = {
                    selectedTab = it
                }
            )

        }

    ) { padding ->
        when(selectedTab){
            0 -> CollectionPointHomePage(
                Modifier.padding(padding)
            )

            1 -> RecyclingRegisterPage(
                Modifier.padding(padding)
            )

            2 -> CollectionPointProfilePage(
                Modifier.padding(padding)
            )

        }

    }

}