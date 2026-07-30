package com.example.recicoin.pages.home

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.recicoin.activities.HomeActivity
import com.example.recicoin.ui.MapCollectionPoint
import com.example.recicoin.ui.MapViewModel
import com.example.recicoin.ui.UserHomeViewModel
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onSurface,
                contentColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = point.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.background
                )


                Spacer(
                    modifier = Modifier.height(8.dp)
                )


                Text(
                    "📍 ${point.address.street}, ${point.address.number}",
                    color = MaterialTheme.colorScheme.surface
                )

                Text(
                    "${point.address.neighborhood} - ${point.address.city}",
                    color = MaterialTheme.colorScheme.surface
                )

                Text(
                    point.address.state,
                    color = MaterialTheme.colorScheme.surface
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    "📞 ${point.phone}",
                    color = MaterialTheme.colorScheme.surface
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )
                Text(
                    "♻ ${point.description}",
                    color = MaterialTheme.colorScheme.surface
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
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
        Spacer(
            modifier = Modifier.height(20.dp)
        )
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
                .padding(16.dp)
        ) {

            Text(
                text = "Bem vindo(a) ao ReciCoin!",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Saldo: ${userHomeViewModel.recicoins.value} r¢",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold
            )

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