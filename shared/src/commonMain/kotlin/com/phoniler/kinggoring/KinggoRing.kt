package com.phoniler.kinggoring

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.phoniler.kinggoring.model.AnalBloodSugarPage
import com.phoniler.kinggoring.model.AnalExercisePage
import com.phoniler.kinggoring.model.AnalInsulinPage
import com.phoniler.kinggoring.model.AnalMealPage
import com.phoniler.kinggoring.model.CameraPage
import com.phoniler.kinggoring.model.ChatPage
import com.phoniler.kinggoring.model.MainPage
import com.phoniler.kinggoring.model.Page
import com.phoniler.kinggoring.model.PictureData
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

enum class ExternalKinggoRingEvent {
    Next,
    Previous,
    ReturnBack,
}

@Composable
fun KinggoRingCommon(dependencies: Dependencies) {
    CompositionLocalProvider(
        LocalImageProvider provides dependencies.imageProvider,
        LocalInternalEvents provides dependencies.externalEvents,
    ) {
        KinggoRingWithProvidedDependencies(dependencies.pictures)
    }
}

@Composable
fun KinggoRingWithProvidedDependencies(pictures: SnapshotStateList<PictureData>) {
    val selectedPictureIndex = rememberSaveable { mutableStateOf(0) }
    val navigationStackSaver =
        Saver<NavigationStack<Page>, List<String>>(
            save = { it.stack.map { page -> page::class.simpleName ?: "" } },
            restore = { savedList ->
                NavigationStack(
                    *savedList
                        .mapNotNull { className ->
                            when (className) {
                                "TaggedPage" -> TaggedPage()
                                "CameraPage" -> CameraPage()
                                "MainPage" -> MainPage()
                                "ChatPage" -> ChatPage()
                                "AnalInsulinPage" -> AnalInsulinPage()
                                "AnalBloodSugarPage" -> AnalBloodSugarPage()
                                "AnalExercisePage" -> AnalExercisePage()
                                "AnalMealPage" -> AnalMealPage()
                                else -> null
                            }
                        }.toTypedArray(),
                )
            },
        )
    val navigationStack =
        rememberSaveable(saver = navigationStackSaver) {
            NavigationStack(MainPage())
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
                TaggedScreen(
                    onMeal = {
                        navigationStack.push(CameraPage())
                    },
                )
            }
            is CameraPage -> {
                CameraScreen(
                    onBack = { resetSelectedPicture ->
                        if (resetSelectedPicture) {
                            selectedPictureIndex.value = 0
                        }
                        navigationStack.back()
                    },
                )
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
