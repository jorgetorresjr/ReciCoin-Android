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
import com.example.recicoin.pages.RegisterMaterialPage
import com.example.recicoin.pages.home.CollectionPointHomePage
import com.example.recicoin.pages.profile.CollectionPointProfilePage
import com.example.recicoin.ui.nav.CollectionPointBottomBar
import com.google.firebase.firestore.DocumentSnapshot

@Composable
fun CollectionPointMainPage(modifier: Modifier) {
    var selectedTab by remember {
        mutableStateOf(0)
    }
    var selectedUser by remember {
        mutableStateOf<DocumentSnapshot?>(null)
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

            1 -> {

                if (selectedUser == null) {

                    RecyclingRegisterPage(
                        padding = Modifier.padding(padding),
                        onUserSelected = {
                            selectedUser = it
                        }
                    )

                } else {

                    RegisterMaterialPage(
                        userId = selectedUser!!.id,
                        userName = selectedUser!!.getString("name") ?: "",
                        modifier = Modifier.padding(padding),
                        onBack = {
                            selectedUser = null
                        }
                    )

                }

            }

            2 -> CollectionPointProfilePage(
                padding = padding
            )

        }

    }

}