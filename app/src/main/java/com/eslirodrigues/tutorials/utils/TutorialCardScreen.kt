package com.eslirodrigues.tutorials.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eslirodrigues.tutorials.R

@Composable
fun TutorialCardScreen() {
    val columnScrollableState = rememberScrollState()

    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp).verticalScroll(columnScrollableState),
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(onClick = { /*TODO*/ }) {
            TutorialCardContent()
        }

        ElevatedCard(onClick = { /*TODO*/ }) {
            TutorialCardContent()
        }

        OutlinedCard(onClick = { /*TODO*/ }) {
            TutorialCardContent()
        }
    }
}

@Composable
fun TutorialCardContent() {
    Column(
        modifier = Modifier.padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Icon(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "Tutorial")
        Text(text = "Tutorials", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text(text = "Github Code", fontSize = 16.sp, fontWeight = FontWeight.Light)
        Text(text = "Find the information about the code", fontSize = 16.sp)
        Row(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
            OutlinedButton(onClick = { /*TODO*/ }) { Text(text = "Close") }
            Spacer(modifier = Modifier.padding(horizontal = 10.dp))
            Button(onClick = { /*TODO*/ }) { Text(text = "Open") }
        }
    }
}