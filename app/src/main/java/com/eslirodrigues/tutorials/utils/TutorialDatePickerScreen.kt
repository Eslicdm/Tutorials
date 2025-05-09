package com.eslirodrigues.tutorials.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.ConfigurationCompat
import java.text.SimpleDateFormat
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorialDatePickerScreen() {
    var showDialog by remember { mutableStateOf(false) }

    var selectedCustomFormattedDate by remember { mutableStateOf("") }
    var selectedDatePickerFormattedDate by remember { mutableStateOf("") }

    val titlePadding = PaddingValues(start = 24.dp, end = 12.dp, top = 16.dp)
    val headlinePadding = PaddingValues(start = 24.dp, end = 12.dp, bottom = 12.dp)

    // locale = Api 24> LocalConfiguration.current.locales[0] or material 3: CalendarLocale.getDefault()
    val locale = ConfigurationCompat.getLocales(LocalConfiguration.current)[0] ?: Locale.getDefault()
    val datePickerFormatter = remember { DatePickerDefaults.dateFormatter() }
    val customFormat = remember { SimpleDateFormat("dd/MM/yyyy", locale) }
    val displayedDate = customFormat.parse("15/10/2023")?.time
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = null,
        initialDisplayedMonthMillis = displayedDate,
        yearRange = IntRange(2020, 2025),
        initialDisplayMode = DisplayMode.Picker,
    )

    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp, Alignment.CenterVertically)
    ) {
        Button(onClick = { showDialog = !showDialog }) { Text(text = "Show/Hide") }
        Text(text = selectedCustomFormattedDate, fontSize = 28.sp)
        Text(text = selectedDatePickerFormattedDate, fontSize = 28.sp)
    }

    if (showDialog) {
        DatePickerDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                        selectedCustomFormattedDate = customFormat.format(datePickerState.selectedDateMillis)
                        selectedDatePickerFormattedDate = datePickerFormatter.formatDate(
                            dateMillis = datePickerState.selectedDateMillis,
                            locale = locale
                        ).orEmpty()
                    },
                ) { Text("OK") }
            },
            dismissButton = { TextButton(onClick = { showDialog = false }) { Text("Cancel") } },
        ) { 
            DatePicker(
                state = datePickerState,
                title = {
                    Text(
                        modifier = Modifier.padding(titlePadding),
                        text = when (datePickerState.displayMode) {
                            DisplayMode.Picker -> "Select Picker Mode"
                            DisplayMode.Input -> "Select Input Mode"
                            else -> "Select Date"
                        }
                    )
                },
                headline = {
                    DatePickerDefaults.DatePickerHeadline(
                        selectedDateMillis = datePickerState.selectedDateMillis,
                        displayMode = datePickerState.displayMode,
                        dateFormatter = datePickerFormatter,
                        modifier = Modifier.padding(headlinePadding)
                    )
                },
                showModeToggle = true,
                colors = DatePickerDefaults.colors()
            ) 
        }
    }
}