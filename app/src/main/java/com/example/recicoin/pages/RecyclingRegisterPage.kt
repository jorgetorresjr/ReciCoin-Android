package com.example.recicoin.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recicoin.ui.CollectionRegisterViewModel
import com.google.firebase.firestore.DocumentSnapshot


@Composable
fun RecyclingRegisterPage(
    padding: Modifier = Modifier,
    viewModel: CollectionRegisterViewModel = viewModel(),
    onUserSelected: (DocumentSnapshot) -> Unit
) {
    Column(
        modifier = padding
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Registrar reciclagem",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(
            Modifier.height(16.dp)
        )

        OutlinedTextField(
            value = viewModel.search.value,
            onValueChange = {
                viewModel.search.value = it
            },
            label = {
                Text("CPF ou email")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            Modifier.height(12.dp)
        )
        Button(
            onClick = {
                viewModel.searchUser()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Pesquisar usuário")

        }

        Spacer(
            Modifier.height(20.dp)
        )

        viewModel.userFound.value?.let { user ->
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        "Usuário encontrado",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(
                        Modifier.height(8.dp)
                    )

                    Text(
                        user.getString("name") ?: ""
                    )

                    Text(
                        "Saldo: ${user.getLong("recicoins") ?: 0} r¢"
                    )

                    Spacer(
                        Modifier.height(12.dp)
                    )

                    Button(
                        onClick = {
                            viewModel.userFound.value?.let {
                                onUserSelected(it)
                            }
                        }
                    ) {
                        Text("Registrar materiais")
                    }

                }
            }
        }

        if(viewModel.error.value.isNotEmpty()) {
            Spacer(
                Modifier.height(12.dp)
            )
            Text(
                viewModel.error.value
            )
        }

    }

}