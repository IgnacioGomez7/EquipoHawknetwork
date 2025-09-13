package com.example.equipohawknetwork.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.equipohawknetwork.model.EventViewModel
import com.example.equipohawknetwork.model.Priority
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventFormScreen(
    onSaved: () -> Unit,
    vm: EventViewModel = hiltViewModel()
) {
    val haptics = LocalHapticFeedback.current

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    // DatePicker
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val selectedDateMillis = datePickerState.selectedDateMillis ?: System.currentTimeMillis()
    val sdf = remember { SimpleDateFormat("dd MMM yyyy", Locale.getDefault()) }

    // Prioridad (Dropdown simple)
    var priority by remember { mutableStateOf(Priority.MEDIUM) }
    var priorityExpanded by remember { mutableStateOf(false) }

    // Animación al guardar
    var isAdded by remember { mutableStateOf(false) }
    val offset by animateDpAsState(targetValue = if (isAdded) 0.dp else 100.dp, label = "addAnim")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .offset(y = offset),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Nuevo evento", style = MaterialTheme.typography.titleLarge)

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Título") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        if (title.isEmpty()) {
            Text("¡Título requerido!", color = Color.Red, style = MaterialTheme.typography.bodySmall)
        }

        // Fecha (abre DatePicker)
        OutlinedTextField(
            value = sdf.format(Date(selectedDateMillis)),
            onValueChange = {},
            label = { Text("Fecha") },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showDatePicker = true }) {
                    Icon(Icons.Filled.DateRange, contentDescription = "Elegir fecha")
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        // Prioridad (DropdownMenu anclado al TextField)
        Box {
            OutlinedTextField(
                value = when (priority) {
                    Priority.LOW -> "Baja"
                    Priority.MEDIUM -> "Media"
                    Priority.HIGH -> "Alta"
                },
                onValueChange = {},
                label = { Text("Prioridad") },
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { priorityExpanded = true }
            )
            DropdownMenu(
                expanded = priorityExpanded,
                onDismissRequest = { priorityExpanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Baja") },
                    onClick = { priority = Priority.LOW; priorityExpanded = false }
                )
                DropdownMenuItem(
                    text = { Text("Media") },
                    onClick = { priority = Priority.MEDIUM; priorityExpanded = false }
                )
                DropdownMenuItem(
                    text = { Text("Alta") },
                    onClick = { priority = Priority.HIGH; priorityExpanded = false }
                )
            }
        }

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (title.isBlank()) return@Button
                vm.addEvent(title.trim(), selectedDateMillis, priority, description.trim())
                isAdded = true
                haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                onSaved()
            },
            enabled = title.isNotBlank()
        ) { Text("Guardar") }
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = { showDatePicker = false }) { Text("OK") }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) { Text("Cancelar") }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}
