package com.antisnusbolaget.slutasnusa2.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.antisnusbolaget.slutasnusa2.ui.theme.black
import com.antisnusbolaget.slutasnusa2.ui.theme.yellow

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
        colors = DatePickerDefaults.colors(
            containerColor = yellow,
        ),
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
            title = {
                Text(
                    text = "När slutade du?",
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = 24.dp, top = 16.dp),
                )
            },
            headline = {
                Box(contentAlignment = Alignment.Center) {
                    TextBold(
                        text = "Välj datum",
                        fontSize = 24.sp,
                        modifier = Modifier
                            .padding(start = 24.dp, bottom = 8.dp)
                            .fillMaxWidth(),
                    )
                }
            },
            colors = DatePickerDefaults.colors(
                titleContentColor = black,
                headlineContentColor = black,
                subheadContentColor = black,
                todayDateBorderColor = black,
                todayContentColor = black,
                selectedDayContentColor = yellow,
                selectedDayContainerColor = black,
                currentYearContentColor = black,
                selectedYearContainerColor = black,
                selectedYearContentColor = yellow,
                containerColor = yellow,
                yearContentColor = black,

            ),
            state = datePickerState,
            dateValidator = { selectedDate ->
                isDateInThePast(selectedDate)
            },
        )
    }
}

private fun isDateInThePast(selectedDate: Long): Boolean =
    System.currentTimeMillis() - selectedDate > 0
