package com.phoniler.kinggoring

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.phoniler.kinggoring.view.KinggoRingAndroid
import com.phoniler.kinggoring.view.MainScreen
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow

class MainActivity : ComponentActivity() {
    private val externalEvents =
        MutableSharedFlow<ExternalKinggoRingEvent>(
            replay = 0,
            extraBufferCapacity = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST,
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KinggoRingAndroid(externalEvents)
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    val externalEvents =
        MutableSharedFlow<ExternalKinggoRingEvent>(
            replay = 0,
            extraBufferCapacity = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST,
        )

    KinggoRingAndroid(externalEvents)
}
