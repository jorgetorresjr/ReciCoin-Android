package com.example.recicoin.pages.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.recicoin.pages.ProductListPage
import com.example.recicoin.pages.home.CompanyHomePage
import com.example.recicoin.pages.profile.CompanyProfilePage
import com.example.recicoin.ui.nav.CompanyBottomBar

@Composable
fun CompanyMainPage(modifier: Modifier) {
    var selectedTab by remember {
        mutableStateOf(0)
    }

    Scaffold(
        bottomBar = {
            CompanyBottomBar (
                selectedTab = selectedTab,
                onTabSelected = {
                    selectedTab = it
                }
            )

        }

    ) { padding ->
        when(selectedTab){
            0 -> CompanyHomePage(
                Modifier.padding(padding)
            )

            1 -> ProductListPage(
                Modifier.padding(padding)
            )

            2 -> CompanyProfilePage(
                Modifier.padding(padding)
            )

        }

    }
}