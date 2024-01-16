package com.eslirodrigues.tutorials.glance_widget.ui.widget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.action.ActionParameters
import androidx.glance.action.actionParametersOf
import androidx.glance.action.actionStartActivity
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.SizeMode
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.Text
import com.eslirodrigues.tutorials.MainActivity

private val tutorialGlanceNumberKey = ActionParameters.Key<Int>("numberKey")

class TutorialGlanceWidget : GlanceAppWidget() {
    override val sizeMode: SizeMode = SizeMode.Single

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            val preferences = currentState<Preferences>()
            val number = preferences[intPreferencesKey(tutorialGlanceNumberKey.name)] ?: 22
            WidgetContent(number)
        }
    }

    @Composable
    private fun WidgetContent(number: Int) {
        Column(
            modifier = GlanceModifier.fillMaxSize().background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = number.toString(), modifier = GlanceModifier.padding(12.dp))
            Row(horizontalAlignment = Alignment.CenterHorizontally) {
                Button(
                    text = "Count",
                    onClick = actionRunCallback<IncreaseAction>(
                        actionParametersOf(tutorialGlanceNumberKey to number)
                    )
                )
                Button(
                    text = "App",
                    onClick = actionStartActivity<MainActivity>()
                )
            }
        }
    }
}

class IncreaseAction : ActionCallback {
    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {
        val number = parameters[tutorialGlanceNumberKey] ?: 0
        updateAppWidgetState(context, glanceId) { mutablePref ->
            mutablePref[intPreferencesKey(tutorialGlanceNumberKey.name)] =
                if (number >= 10) 0 else number + 1
        }
        TutorialGlanceWidget().update(context, glanceId)
    }
}

class TutorialGlanceWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget = TutorialGlanceWidget()
}