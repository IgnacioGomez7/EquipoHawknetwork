package com.example.equipohawknetwork.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.equipohawknetwork.model.Event
import com.example.equipohawknetwork.model.Priority
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun EventCard(event: Event, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    val sdf = remember { SimpleDateFormat("dd MMM yyyy", Locale.getDefault()) }

    Card(modifier = modifier.animateContentSize()) {
        Column(Modifier.padding(16.dp)) {
            Text(event.title, style = MaterialTheme.typography.titleMedium)
            Text(sdf.format(Date(event.dateMillis)), style = MaterialTheme.typography.bodySmall)
            Text(
                "Prioridad: " + when (event.priority) {
                    Priority.LOW -> "Baja"; Priority.MEDIUM -> "Media"; Priority.HIGH -> "Alta"
                },
                style = MaterialTheme.typography.bodySmall
            )
            if (expanded) {
                Spacer(Modifier.padding(top = 8.dp))
                Text(event.description, style = MaterialTheme.typography.bodyMedium)
            }
            IconButton(onClick = { expanded = !expanded }) {
                Icon(if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore, null)
            }
        }
    }
}
