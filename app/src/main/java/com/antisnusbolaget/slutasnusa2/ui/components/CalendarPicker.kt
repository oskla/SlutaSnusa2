package com.antisnusbolaget.slutasnusa2.ui.components

import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calendar(onDateSelected: (Long) -> Unit) {
    val snackState = remember { SnackbarHostState() }
    SnackbarHost(hostState = snackState, Modifier)
    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value) {
        val datePickerState = rememberDatePickerState()
        val confirmEnabled =
            remember { derivedStateOf { datePickerState.selectedDateMillis != null } }
        DatePickerDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        onDateSelected(datePickerState.selectedDateMillis!!)
                    },
                    enabled = confirmEnabled.value,
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    },
                ) {
                    Text("Cancel")
                }
            },
        ) {
            DatePicker(state = datePickerState)
        }
    }
}
