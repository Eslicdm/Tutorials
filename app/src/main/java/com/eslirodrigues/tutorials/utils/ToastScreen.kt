package com.eslirodrigues.tutorials.utils

import android.widget.Toast
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.eslirodrigues.tutorials.R

@Composable
fun ToastScreen() {
    val context = LocalContext.current
    Button(onClick = {
        Toast.makeText(
            context,
            context.getString(R.string.toast_showed),
            Toast.LENGTH_SHORT).show()
    }) {
        Text(text = "Show Toast")
    }
}