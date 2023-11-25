package com.eslirodrigues.tutorials

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.runtime.mutableStateOf
import com.eslirodrigues.tutorials.contact_provider_intent.ui.screen.TutorialIntentContactScreen
import com.eslirodrigues.tutorials.ui.theme.TutorialsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val contactUri = mutableStateOf<Uri?>(null)
        val contactPicker = registerForActivityResult(ActivityResultContracts.PickContact()) { uri ->
            contactUri.value = uri
        }

        setContent {
            TutorialsTheme {
                TutorialIntentContactScreen(
                    contactUri = contactUri.value,
                    onGetContactClick = { contactPicker.launch() }
                )
            }
        }
    }
}