package com.eslirodrigues.tutorials.permission.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.eslirodrigues.tutorials.permission.ui.components.TutorialMultiPermissionsButton
import com.eslirodrigues.tutorials.permission.ui.components.TutorialSinglePermissionButton

@Composable
fun TutorialPermissionScreen() {
    var multiGranted by remember { mutableStateOf(false) }
    var singleGranted by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TutorialMultiPermissionsButton(
            onClick = { multiGranted = true },
            text = "Ask Multi Permissions"
        )
        if (multiGranted) Text(text = "Multi Granted")
        TutorialSinglePermissionButton(
            onClick = { singleGranted = true },
            text = "Ask Single Permissions"
        )
        if (singleGranted) Text(text = "Single Granted")
    }
}