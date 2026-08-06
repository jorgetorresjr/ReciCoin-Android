package com.example.recicoin.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recicoin.model.MaterialType
import com.example.recicoin.ui.viewmodel.RegisterMaterialViewModel


@Composable
fun RegisterMaterialPage(
    userId: String,
    userName: String,
    modifier: Modifier = Modifier,
    viewModel: RegisterMaterialViewModel = viewModel(),
    onBack: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Registrar material",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(
            Modifier.height(16.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                Modifier.padding(16.dp)
            ) {
                Text(
                    "Usuário:"
                )

                Text(
                    userName,
                    style = MaterialTheme.typography.titleMedium
                )

            }
        }


        Spacer(
            Modifier.height(20.dp)
        )

        Text("Material")

        MaterialDropdown(
            selected = viewModel.material,
            onSelect = {
                viewModel.material = it
            }
        )


        Spacer(
            Modifier.height(16.dp)
        )


        OutlinedTextField(
            value = viewModel.weight,
            onValueChange = {
                viewModel.weight = it
            },
            label = {
                Text("Peso (kg)")
            },
            modifier = Modifier.fillMaxWidth()
        )


        Spacer(
            Modifier.height(16.dp)
        )


        Text(
            "ReciCoins gerados: ${viewModel.calculateCoins()}"
        )


        Spacer(
            Modifier.height(20.dp)
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {

                viewModel.registerRecycle(
                    userId
                )
                onBack()
            }
        ) {
            Text("Confirmar coleta")
        }

    }

}

@Composable
fun MaterialDropdown(
    selected: MaterialType,
    onSelect: (MaterialType) -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    val materials = MaterialType.entries

    Box {
        Button(
            onClick = {
                expanded = true
            }
        ) {
            Text(selected.displayName())

        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {

            materials.forEach { material ->
                DropdownMenuItem(
                    text = {
                        Text(material.displayName())
                    },
                    onClick = {
                        onSelect(material)
                        expanded = false
                    }
                )

            }

        }

    }

}

fun MaterialType.displayName(): String =
    when (this) {
        MaterialType.PAPER -> "Papel"
        MaterialType.PLASTIC -> "Plástico"
        MaterialType.GLASS -> "Vidro"
        MaterialType.METAL -> "Metal"
        MaterialType.ELECTRONICS -> "Eletrônicos"
    }