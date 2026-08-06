package com.example.recicoin.pages.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Recycling
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import com.example.recicoin.ui.viewmodel.MapCollectionPoint
import com.example.recicoin.ui.viewmodel.MapViewModel
import com.example.recicoin.ui.viewmodel.UserHomeViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollectionPointSheet(
    point: MapCollectionPoint,
    onClose: () -> Unit
) {

    Column(
        modifier = Modifier.padding(20.dp)
    ) {


        Text(
            point.name,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.background
        )


        Spacer(
            Modifier.height(16.dp)
        )


        Text(
            "Localização:",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.surfaceVariant
        )


        Text(
            "${point.address.street}, ${point.address.number}",
            color = MaterialTheme.colorScheme.surface
        )


        Text(
            "${point.address.neighborhood} - ${point.address.city}/${point.address.state}",
            color = MaterialTheme.colorScheme.surface
        )


        Spacer(
            Modifier.height(12.dp)
        )


        Text(
            "Contato:",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.surfaceVariant
        )


        Text(
            point.phone,
            color = MaterialTheme.colorScheme.surface
        )


        Spacer(
            Modifier.height(12.dp)
        )


        AssistChip(
            onClick = {},
            label = {
                Text(point.description)
            },
            colors = AssistChipDefaults.assistChipColors(
                containerColor = MaterialTheme.colorScheme.background,
                labelColor = MaterialTheme.colorScheme.primary
            )
        )


        Spacer(
            Modifier.height(20.dp)
        )


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {

            Button(
                onClick = onClose
            ) {
                Text("Fechar")
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserHomePage(
    modifier: Modifier = Modifier,
    viewModel: MapViewModel = viewModel(),
    userHomeViewModel: UserHomeViewModel = viewModel()
) {
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(-8.0476, -34.8770),
            12f
        )
    }

    var selectedPoint by remember {
        mutableStateOf<MapCollectionPoint?>(null)
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
            ) {

                Text(
                    text = "ReciCoin",
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 8.dp, bottom = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Saldo:",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(Modifier.width(4.dp))

                    Text(
                        text = "${userHomeViewModel.recicoins.value} r¢",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

        GoogleMap(
            modifier = Modifier.weight(1f),
            cameraPositionState = cameraPositionState
        ) {
            viewModel.collectionPoints.forEach { point ->
                Marker(
                    state = MarkerState(
                        LatLng(
                            point.latitude,
                            point.longitude
                        )
                    ),
                    title = point.name,
                    onClick = {
                        selectedPoint = point
                        true
                    }
                )
            }
        }

        selectedPoint?.let { point ->
            ModalBottomSheet(
                onDismissRequest = {
                    selectedPoint = null
                },
                containerColor = MaterialTheme.colorScheme.onSurface
            ) {
                CollectionPointSheet(
                    point = point,
                    onClose = {
                        selectedPoint = null
                    }
                )
            }
        }
    }
}