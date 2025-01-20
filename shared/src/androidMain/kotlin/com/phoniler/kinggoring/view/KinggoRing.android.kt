package com.phoniler.kinggoring.view

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.phoniler.kinggoring.Dependencies
import com.phoniler.kinggoring.ExternalKinggoRingEvent
import com.phoniler.kinggoring.KinggoRingCommon
import com.phoniler.kinggoring.ioDispatcher
import com.phoniler.kinggoring.storage.AndroidImageStorage
import com.phoniler.kinggoring.style.KinggoRingTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

@Composable
fun KinggoRingAndroid(
    externalEvents: Flow<ExternalKinggoRingEvent>,
    intentData: Any?,
    finishApp: () -> Unit,
) {
    val context: Context = LocalContext.current
    val ioScope = rememberCoroutineScope { ioDispatcher }
    val dependencies =
        remember(context, ioScope) {
            getDependencies(context, ioScope, externalEvents)
        }
    KinggoRingTheme {
        KinggoRingCommon(dependencies, intentData) {
            finishApp()
        }
    }
}

private fun getDependencies(
    context: Context,
    ioScope: CoroutineScope,
    externalEvents: Flow<ExternalKinggoRingEvent>,
) = object : Dependencies() {
    override val imageStorage: AndroidImageStorage = AndroidImageStorage(pictures, ioScope, context)
    override val externalEvents: Flow<ExternalKinggoRingEvent> = externalEvents
}
