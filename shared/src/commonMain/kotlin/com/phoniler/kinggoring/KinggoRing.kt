package com.phoniler.kinggoring

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.phoniler.kinggoring.model.AnalysisPage
import com.phoniler.kinggoring.model.CameraPage
import com.phoniler.kinggoring.model.MainPage
import com.phoniler.kinggoring.model.Page
import com.phoniler.kinggoring.model.PictureData
import com.phoniler.kinggoring.model.TaggedPage
import com.phoniler.kinggoring.type.NavType
import com.phoniler.kinggoring.view.AnalysisScreen
import com.phoniler.kinggoring.view.CameraScreen
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
    val navigationStack =
        rememberSaveable(
            saver =
                listSaver<NavigationStack<Page>, Page>(
                    restore = { NavigationStack(*it.toTypedArray()) },
                    save = { it.stack },
                ),
        ) {
            NavigationStack(MainPage())
        }

    val externalEvents = LocalInternalEvents.current
    LaunchedEffect(Unit) {
        externalEvents.collect {
            if (it == ExternalKinggoRingEvent.ReturnBack) {
                navigationStack.back()
            }
        }
    }

    AnimatedContent(targetState = navigationStack.lastWithIndex(), transitionSpec = {
        val previousIdx = initialState.index
        val currentIdx = targetState.index
        val multiplier = if (previousIdx < currentIdx) 1 else -1
        if (initialState.value is MainPage && targetState.value is AnalysisPage) {
            fadeIn() togetherWith fadeOut(tween(durationMillis = 500, 500))
        } else if (initialState.value is AnalysisPage && targetState.value is MainPage) {
            fadeIn() togetherWith fadeOut(tween(delayMillis = 150))
        } else {
            slideInHorizontally { w -> multiplier * w } togetherWith
                slideOutHorizontally { w -> multiplier * -1 * w }
        }
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
                MainScreen(
                    mainView = page.mainView,
                    onAnalClick = { target ->
                        navigationStack.push(AnalysisPage(target))
                    },
                )
            }
            is AnalysisPage -> {
                AnalysisScreen(
                    analView = page.analView,
                    onBackClick = {
                        navigationStack.back()
                    },
                    onChatClick = {
                        navigationStack.back()
                        navigationStack.replaceLast(MainPage(NavType.CHATBOT))
                    },
                )
            }
        }
    }
}
