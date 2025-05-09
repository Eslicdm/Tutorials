package com.eslirodrigues.tutorials.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.RichTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolTipScreen() {
    val scope = rememberCoroutineScope()
    val plainTooltipState = rememberTooltipState()
    val richTooltipState = rememberTooltipState(isPersistent = true)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp, Alignment.CenterVertically)
    ) {
        TooltipBox(
            positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
            state = plainTooltipState,
            tooltip = {
                PlainTooltip {
                    Text("Delete This Item - PlainToolTip Text")
                }
            },
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete"
            )
        }
        Button(onClick = { scope.launch { plainTooltipState.show() } }) {
            Text("Show PlainTooltip")
        }

        TooltipBox(
            positionProvider = TooltipDefaults.rememberRichTooltipPositionProvider(),
            state = richTooltipState,
            tooltip = {
                RichTooltip(
                    title = { Text(text = "Share - Title RichToolTip") },
                    text = { Text(text = "Share with your friends - Text RichToolTip") },
                    action = {
                        TextButton(onClick = {
                            scope.launch { richTooltipState.dismiss() }
                        }) {
                            Text(text = "Dismiss")
                        }
                    }
                )
            }
        ) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = "Share"
            )
        }
        Button(onClick = { scope.launch { richTooltipState.show() } }) {
            Text("Show RichToolTip")
        }
    }
}