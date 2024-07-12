package com.eslirodrigues.tutorials.camera_video.ui.screen

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.provider.MediaStore
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.OptIn
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalZeroShutterLag
import androidx.camera.core.MirrorMode.MIRROR_MODE_ON_FRONT_ONLY
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.video.FallbackStrategy
import androidx.camera.video.MediaStoreOutputOptions
import androidx.camera.video.Quality
import androidx.camera.video.QualitySelector
import androidx.camera.video.Recorder
import androidx.camera.video.Recording
import androidx.camera.video.VideoCapture
import androidx.camera.video.VideoRecordEvent
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.media3.common.util.UnstableApi
import com.eslirodrigues.tutorials.R
import com.eslirodrigues.tutorials.camera_video.ui.components.TutorialCameraVideoPermissionButton
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun TutorialCameraVideoScreen() {
    var showCameraVideo by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
        TutorialCameraVideoPermissionButton(
            onClick = { showCameraVideo = !showCameraVideo },
            text = "Camera Video"
        )
        if (showCameraVideo) TutorialCameraVideo()
    }
}

@OptIn(UnstableApi::class)
@Composable
private fun TutorialCameraVideo() {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val isRecording = remember { mutableStateOf(false) }

    AndroidView(
        modifier = Modifier
            .size(200.dp)
            .padding(top = 70.dp),
        factory = { viewContext ->
            cameraUseCases(context, lifecycleOwner, viewContext)
        }
    )
    Canvas(modifier = Modifier
        .size(50.dp)
        .clickable {
            if (isRecording.value) recording?.stop() else captureVideo(context, isRecording)
        },
        onDraw = { drawCircle(if (isRecording.value) Color.Red else Color.Gray) }
    )
}

private var videoCapture: VideoCapture<Recorder>? = null
private var recording: Recording? = null

@SuppressLint("MissingPermission")
private fun captureVideo(
    context: Context,
    isRecording: MutableState<Boolean>,
) {
    val videoCapture = videoCapture ?: return

    val curRecording = recording
    if (curRecording != null) {
        curRecording.stop()
        recording = null
        return
    }

    val name =
        SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS", Locale.US).format(System.currentTimeMillis())
    val contentValues = ContentValues().apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, name)
        put(MediaStore.MediaColumns.MIME_TYPE, "video/mp4")
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
            val appName = context.resources.getString(R.string.app_name)
            put(MediaStore.Images.Media.RELATIVE_PATH, "Movies/${appName}")
        }
    }

    val outputOptions = MediaStoreOutputOptions
        .Builder(context.contentResolver, MediaStore.Video.Media.EXTERNAL_CONTENT_URI)
        .setContentValues(contentValues)
        .build()

    recording = videoCapture.output
        .prepareRecording(context, outputOptions)
        .withAudioEnabled()
        .start(ContextCompat.getMainExecutor(context)) { recordEvent ->
            when(recordEvent) {
                is VideoRecordEvent.Start -> isRecording.value = true
                is VideoRecordEvent.Finalize -> {
                    if (!recordEvent.hasError()) {
                        val msg = "Video Success: ${recordEvent.outputResults.outputUri}"
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                    } else {
                        recording?.close()
                        recording = null
                        val msg = "Video Fail: ${recordEvent.error}"
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                    }
                    isRecording.value = false
                }
            }
        }
}

@OptIn(ExperimentalZeroShutterLag::class)
private fun cameraUseCases(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    viewContext: Context,
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

        val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

        val qualitySelector = QualitySelector.fromOrderedList(
            listOf(Quality.UHD, Quality.FHD, Quality.HD, Quality.SD),
            FallbackStrategy.lowerQualityOrHigherThan(Quality.SD)
        )
        val recorder = Recorder.Builder()
            .setQualitySelector(qualitySelector)
            .build()
        videoCapture = VideoCapture.Builder(recorder)
            .setMirrorMode(MIRROR_MODE_ON_FRONT_ONLY)
            .build()

        try {
            cameraProvider.unbindAll()
            cameraProvider.bindToLifecycle(lifecycleOwner, cameraSelector, preview, videoCapture)
        } catch (e: Exception) {
            Toast.makeText(context, "Preview Fail: ${e.message}", Toast.LENGTH_LONG).show()
        }

    }, ContextCompat.getMainExecutor(context))

    return previewView
}