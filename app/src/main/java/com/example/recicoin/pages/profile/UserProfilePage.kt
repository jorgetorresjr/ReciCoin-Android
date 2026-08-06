package com.example.recicoin.pages.profile

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Recycling
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recicoin.activities.HomeActivity
import com.example.recicoin.model.user.User
import com.example.recicoin.model.user.UserProfile
import com.example.recicoin.model.user.UserType
import com.example.recicoin.ui.viewmodel.UserProfileViewModel
import com.example.recicoin.ui.components.ProfileHeader
import com.example.recicoin.ui.components.ProfileInfoItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfilePage(
    padding: PaddingValues,
    viewModel: UserProfileViewModel = viewModel()
) {
    val activity = LocalActivity.current as HomeActivity
    val name = viewModel.name.value
    val email = viewModel.email.value
    val address = viewModel.address.value
    val recicoins = viewModel.recicoins.value

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (name.isNotBlank()) {
                ProfileHeader(
                    name = name,
                    photoUrl = null
                )

                ElevatedCard {
                    Column {
                        ProfileInfoItem(
                            icon = Icons.Default.Email,
                            title = "Email",
                            value = email
                        )

                        HorizontalDivider()

                        ProfileInfoItem(
                            icon = Icons.Default.LocationOn,
                            title = "Endereço",
                            value = address
                        )

                        HorizontalDivider()

                        ProfileInfoItem(
                            icon = Icons.Default.Recycling,
                            title = "ReciCoins",
                            value = "$recicoins r¢"
                        )
                    }
                }

            } else {
                Text("Carregando perfil...")
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Button(
                    onClick = {
                    }
                ) {
                    Icon(
                        Icons.Default.Edit,
                        contentDescription = null
                    )

                    Spacer(Modifier.width(8.dp))

                    Text("Editar")
                }

                FilledTonalButton(
                    onClick = {
                        activity.logout()
                    }
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.Logout,
                        contentDescription = null
                    )

                    Spacer(Modifier.width(8.dp))

                    Text("Logout")
                }
            }
        }
}