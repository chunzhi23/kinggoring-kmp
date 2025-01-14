package com.phoniler.kinggoring

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.phoniler.kinggoring.model.AnalBloodSugarPage
import com.phoniler.kinggoring.model.AnalExercisePage
import com.phoniler.kinggoring.model.AnalInsulinPage
import com.phoniler.kinggoring.model.AnalMealPage
import com.phoniler.kinggoring.model.CameraPage
import com.phoniler.kinggoring.model.ChatPage
import com.phoniler.kinggoring.model.MainPage
import com.phoniler.kinggoring.model.Page
import com.phoniler.kinggoring.model.TaggedPage
import com.phoniler.kinggoring.view.AnalBloodSugarScreen
import com.phoniler.kinggoring.view.AnalExerciseScreen
import com.phoniler.kinggoring.view.AnalInsulinScreen
import com.phoniler.kinggoring.view.AnalMealScreen
import com.phoniler.kinggoring.view.CameraScreen
import com.phoniler.kinggoring.view.ChatScreen
import com.phoniler.kinggoring.view.MainScreen
import com.phoniler.kinggoring.view.NavigationStack
import com.phoniler.kinggoring.view.TaggedScreen

@Composable
fun KinggoRingCommon() {
    val navigationStack =
        remember {
            NavigationStack<Page>(TaggedPage())
        }

    AnimatedContent(targetState = navigationStack.lastWithIndex(), transitionSpec = {
        val previousIdx = initialState.index
        val currentIdx = targetState.index
        val multiplier = if (previousIdx < currentIdx) 1 else -1
        slideInVertically { w -> multiplier * w } togetherWith
            slideOutVertically { w -> multiplier * -1 * w }
    }) { (_, page) ->
        when (page) {
            is TaggedPage -> {
                TaggedScreen()
            }
            is CameraPage -> {
                CameraScreen()
            }
            is MainPage -> {
                MainScreen()
            }
            is ChatPage -> {
                ChatScreen()
            }
            is AnalInsulinPage -> {
                AnalInsulinScreen()
            }
            is AnalBloodSugarPage -> {
                AnalBloodSugarScreen()
            }
            is AnalExercisePage -> {
                AnalExerciseScreen()
            }
            is AnalMealPage -> {
                AnalMealScreen()
            }
        }
    }
}
