package com.eslirodrigues.tutorials.camera_capture.ui.screen

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.OptIn
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalZeroShutterLag
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import coil.compose.AsyncImage
import com.eslirodrigues.tutorials.R
import com.eslirodrigues.tutorials.camera_capture.ui.components.TutorialCameraCaptureButton
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun TutorialCameraCaptureScreen() {
    var showCameraCapture by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
        TutorialCameraCaptureButton(
            onClick = { showCameraCapture = !showCameraCapture },
            text = "Capture"
        )
        if (showCameraCapture) TutorialCameraCapture()
    }
}

@Composable
private fun TutorialCameraCapture() {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var lastImageUri by rememberSaveable { mutableStateOf<Uri?>(null) }

    AndroidView(
        modifier = Modifier.size(200.dp).padding(top = 70.dp),
        factory = { viewContext ->
            cameraUseCases(context, lifecycleOwner, viewContext)
        }
    )
    Canvas(modifier = Modifier
        .size(50.dp)
        .clickable {
            takePhoto(context) { imageUri ->
                lastImageUri = imageUri
            }
        },
        onDraw = { drawCircle(Color.Gray) }
    )
    AsyncImage(
        modifier = Modifier.size(200.dp),
        model = lastImageUri,
        contentDescription = "Last Image"
    )
}

private var imageCapture: ImageCapture? = null

private fun takePhoto(
    context: Context,
    onCaptureSuccess: (imageUri: Uri?) -> Unit,
) {
    val imageCapture = imageCapture ?: return

    val name =
        SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS", Locale.US).format(System.currentTimeMillis())
    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, name)
        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
            val appName = context.resources.getString(R.string.app_name)
            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/${appName}")
        }
    }

    val outputOptions = ImageCapture.OutputFileOptions.Builder(
        context.contentResolver,
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        contentValues
    ).build()

    imageCapture.takePicture(outputOptions, ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onError(e: ImageCaptureException) {
                Toast.makeText(context, "Capture Fail: ${e.message}", Toast.LENGTH_LONG).show()
            }

            override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                onCaptureSuccess(output.savedUri)
                Toast.makeText(context, "Capture Success: ${output.savedUri}", Toast.LENGTH_LONG)
                    .show()
            }
        }
    )
}


@OptIn(ExperimentalZeroShutterLag::class)
private fun cameraUseCases(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    viewContext: Context
): PreviewView {
    val previewView = PreviewView(viewContext).apply {
        scaleType = PreviewView.ScaleType.FILL_CENTER
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        implementationMode = PreviewView.ImplementationMode.PERFORMANCE
    }

    val cameraProviderFuture = ProcessCameraProvider.getInstance(context)
    cameraProviderFuture.addListener({
        val cameraProvider = cameraProviderFuture.get()

        val preview = Preview.Builder()
            .build().also { it.surfaceProvider = previewView.surfaceProvider }

        imageCapture = ImageCapture.Builder()
            .setCaptureMode(ImageCapture.CAPTURE_MODE_ZERO_SHUTTER_LAG)
            .setFlashMode(ImageCapture.FLASH_MODE_AUTO)
            .build()

        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

        try {
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(lifecycleOwner, cameraSelector, preview, imageCapture)
        } catch (e: Exception) {
            Toast.makeText(context, "Preview Fail: ${e.message}", Toast.LENGTH_LONG).show()
        }

    }, ContextCompat.getMainExecutor(context))

    return previewView
}