package com.eslirodrigues.tutorials.photo_picker.ui.screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickMultipleVisualMedia
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun TutorialPhotoPickerScreen() {
    Column(Modifier.fillMaxSize()) {
        PickOnePhoto()
        PickMultiPhotos()
    }
}

@Composable
private fun PickOnePhoto() {
    var imageUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    val pickMedia = rememberLauncherForActivityResult(PickVisualMedia()) { uri ->
        imageUri = uri
    }

    Button(onClick = { pickMedia.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly)) }) {
        Text(text = "Pick Photo")
    }
    AsyncImage(
        modifier = Modifier.size(200.dp),
        model = imageUri,
        contentDescription = "image"
    )
}

@Composable
private fun PickMultiPhotos() {
    val imagesUri = remember { mutableStateListOf<Uri>() }
    val pickMedias = rememberLauncherForActivityResult(PickMultipleVisualMedia(4)) { uris ->
        uris.forEach { uri -> imagesUri.add(uri) }
    }

    Button(onClick = { pickMedias.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly)) }) {
        Text(text = "Pick Photos")
    }
    LazyColumn {
        items(imagesUri) { image ->
            AsyncImage(
                modifier = Modifier.size(200.dp),
                model = image,
                contentDescription = "image"
            )
        }
    }
}