package com.antisnusbolaget.slutasnusa2.ui.components

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calendar(
    onDateSelected: (Long) -> Unit,
    onDismiss: () -> Unit,
) {
    val snackState = remember { SnackbarHostState() }
    SnackbarHost(hostState = snackState, Modifier)

    val datePickerState = rememberDatePickerState()
    val confirmEnabled = remember {
        derivedStateOf {
            datePickerState.selectedDateMillis != null
        }
    }

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    onDismiss.invoke()
                    onDateSelected(datePickerState.selectedDateMillis ?: 0)
                },
                enabled = confirmEnabled.value,
            ) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismiss.invoke()
                },
            ) {
                Text("Cancel")
            }
        },
    ) {
        DatePicker(
            state = datePickerState,
            dateValidator = { selectedDate ->
                isDateInThePast(selectedDate)
            },
        )
    }
}

private fun isDateInThePast(selectedDate: Long): Boolean = System.currentTimeMillis() - selectedDate > 0
