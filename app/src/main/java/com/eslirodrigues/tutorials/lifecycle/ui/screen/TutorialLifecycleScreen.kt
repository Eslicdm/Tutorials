package com.eslirodrigues.tutorials.lifecycle.ui.screen

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.LifecycleResumeEffect
import androidx.lifecycle.compose.LifecycleStartEffect
import androidx.lifecycle.compose.currentStateAsState


// composition ends before onDestroy
// Uses: start and stop videos, animations, network
// ViewModel handles lifecycle
@Composable
fun TutorialLifecycleScreen() {
    val lifecycleOwner = LocalLifecycleOwner.current
    val currentLifecycleState = lifecycleOwner.lifecycle.currentStateAsState()
    Log.i("Lifecycle", currentLifecycleState.toString())

    LifecycleEventEffect(Lifecycle.Event.ON_CREATE) {
        Log.i("Lifecycle", "Create") // "App Creation"
    }
    LifecycleEventEffect(Lifecycle.Event.ON_START) {
       Log.i("Lifecycle", "Start") // "App Restarted"
    }
    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        Log.i("Lifecycle", "Resume") // "App Visible"
    }
    LifecycleEventEffect(Lifecycle.Event.ON_PAUSE) {
        Log.i("Lifecycle", "Pause") // "App not visible but still working"
    }
    LifecycleEventEffect(Lifecycle.Event.ON_STOP) {
        Log.i("Lifecycle", "Stop") // "App not visible and not working"
    }

    LifecycleStartEffect {
        Log.i("Lifecycle", "StartStart")

        onStopOrDispose {
            Log.i("Lifecycle", "StopStop")
        }
    }

    LifecycleResumeEffect {
        Log.i("Lifecycle", "ResumeResume")

        onPauseOrDispose {
            Log.i("Lifecycle", "PausePause")
        }
    }
}