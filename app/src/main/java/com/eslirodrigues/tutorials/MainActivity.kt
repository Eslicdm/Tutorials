package com.eslirodrigues.tutorials

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.eslirodrigues.tutorials.bottom_sheet.ui.screen.TutorialBottomSheetScreen
import com.eslirodrigues.tutorials.card.ui.screen.TutorialCardScreen
import com.eslirodrigues.tutorials.change_theme.ui.screen.ChangeThemeScreen
import com.eslirodrigues.tutorials.horizontal_pager_on_boarding.ui.screen.TutorialHorizontalPagerOnBoardingScreen
import com.eslirodrigues.tutorials.network_state.ui.screen.TutorialNetworkStateScreen
import com.eslirodrigues.tutorials.progress_indicator.ui.screen.TutorialProgressIndicatorScreen
import com.eslirodrigues.tutorials.swipe_to_dismiss.ui.screen.TutorialSwipeToDismissScreen
import com.eslirodrigues.tutorials.text_field.ui.screen.TutorialTextFieldScreen
import com.eslirodrigues.tutorials.tooltip.ui.screen.TutorialToolTipScreen
import com.eslirodrigues.tutorials.ui.theme.TutorialsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TutorialsTheme {
                TutorialSwipeToDismissScreen()
            }
        }
    }
}