package com.eslirodrigues.tutorials.utils.countdowntimer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun TutorialCountDownTimerScreen(viewModel: CountDownTimerViewModel = viewModel()) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        viewModel.apply {
            Text(text = timerText.value, fontSize = 28.sp)
            Button(onClick = {
                if (isPlaying.value) stopCountDownTimer() else startCountDownTimer()
            }) {
                Text(text = if (isPlaying.value) "Stop CountDown" else "Start CountDown")
            }
            Button(onClick = {
                resetCountDownTimer()
            }) {
                Text(text = "Reset Timer")
            }
        }
    }
}
