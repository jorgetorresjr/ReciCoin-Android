package com.example.recicoin.pages.profile

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recicoin.activities.HomeActivity
import com.example.recicoin.ui.CompanyProfileViewModel
import com.example.recicoin.ui.components.ProfileHeader
import com.example.recicoin.ui.components.ProfileInfoItem


@Composable
fun CompanyProfilePage(
    padding: PaddingValues,
    viewModel: CompanyProfileViewModel = viewModel()
) {

    val activity = LocalActivity.current as HomeActivity

    val name = viewModel.name.value
    val email = viewModel.email.value
    val phone = viewModel.phone.value
    val description = viewModel.description.value


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {


        ProfileHeader(
            name = name,
            photoUrl = viewModel.logoUrl.value
        )


        ElevatedCard(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column {

                ProfileInfoItem(
                    icon = Icons.Default.Email,
                    title = "Email",
                    value = email
                )


                HorizontalDivider()


                ProfileInfoItem(
                    icon = Icons.Default.Phone,
                    title = "Telefone",
                    value = phone
                )


                HorizontalDivider()


                ProfileInfoItem(
                    icon = Icons.Default.Business,
                    title = "Empresa",
                    value = description
                )

            }
        }


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {


            Button(
                modifier = Modifier.weight(1f),
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
                modifier = Modifier.weight(1f),
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