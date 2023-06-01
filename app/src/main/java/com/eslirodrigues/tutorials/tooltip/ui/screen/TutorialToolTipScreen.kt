package com.eslirodrigues.tutorials.tooltip.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.PlainTooltipBox
import androidx.compose.material3.RichTooltipBox
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberPlainTooltipState
import androidx.compose.material3.rememberRichTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TutorialToolTipScreen() {
    val scope = rememberCoroutineScope()
    val plainTooltipState = rememberPlainTooltipState()
    val richTooltipState = rememberRichTooltipState(isPersistent = true)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp, Alignment.CenterVertically)
    ) {
        PlainTooltipBox(
            tooltip = { Text("Delete This Item - PlainToolTip Text") },
            tooltipState = plainTooltipState
        ) {
            Icon(
                modifier = Modifier.tooltipTrigger() ,
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete"
            )
        }
        Button(onClick = { scope.launch { plainTooltipState.show() } }) {
            Text("Show PlainTooltip")
        }
        RichTooltipBox(
            tooltipState = richTooltipState,
            title = { Text(text = "Share - Title RichToolTip") },
            action = {
                TextButton(onClick = {
                    scope.launch { richTooltipState.dismiss() }
                }) {
                    Text(text = "Dismiss")
                }
            },
            text = { Text(text = "Share with your friends - Text RichToolTip") }
        ) {
            Icon(
                modifier = Modifier.tooltipTrigger() ,
                imageVector = Icons.Default.Share,
                contentDescription = "Share"
            )
        }
        Button(onClick = { scope.launch { richTooltipState.show() } }) {
            Text("Show RichToolTip")
        }
    }
}