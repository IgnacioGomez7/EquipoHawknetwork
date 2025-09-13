package com.example.equipohawknetwork.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.equipohawknetwork.model.EventViewModel

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun EventListScreen(
    onAddClick: () -> Unit,
    vm: EventViewModel = hiltViewModel()
) {
    val haptics = LocalHapticFeedback.current

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                haptics.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                onAddClick()
            }) { Icon(Icons.Filled.Add, contentDescription = "Agregar") }
        }
    ) { padding ->
        if (vm.events.isEmpty()) {
            Box(
                Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    "No hay eventos. Toca + para agregar uno.",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(16.dp),
                contentPadding = PaddingValues(bottom = 96.dp)
            ) {
                items(vm.events, key = { it.id }) { item ->
                    val dismissState = rememberDismissState(
                        confirmStateChange = { v ->
                            if (v == DismissValue.DismissedToEnd || v == DismissValue.DismissedToStart) {
                                vm.removeEvent(item.id)
                                haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                            }
                            true
                        }
                    )
                    SwipeToDismiss(
                        state = dismissState,
                        background = { /* fondo opcional */ },
                        directions = setOf(
                            DismissDirection.StartToEnd,
                            DismissDirection.EndToStart
                        )
                    ) {
                        EventCard(
                            event = item,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .animateItemPlacement()
                        )
                    }
                }
            }
        }
    }
}
