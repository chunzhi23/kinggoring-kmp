package com.phoniler.kinggoring.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.phoniler.kinggoring.LocalImageProvider
import com.phoniler.kinggoring.component.TopLayout
import com.phoniler.kinggoring.icon.IconOutlinedArrowBack
import kotlinx.coroutines.delay

@Composable
fun CameraScreen(onBack: (resetSelectedPicture: Boolean) -> Unit) {
    val imageProvider = LocalImageProvider.current
    var showCamera by remember { mutableStateOf(false) }
    LaunchedEffect(onBack) {
        if (!showCamera) {
            delay(300)
            showCamera = true
        }
    }

    Box(Modifier.fillMaxSize().background(Color.Black)) {
        if (showCamera) {
            CameraView(Modifier.fillMaxSize(), onCapture = { picture, image ->
                imageProvider.saveImage(picture, image)
                onBack(true)
            })
        }
        TopLayout(
            alignLeftContent = {
                FilledIconButton(
                    onClick = { onBack(false) },
                ) {
                    Icon(
                        imageVector = IconOutlinedArrowBack,
                        contentDescription = "Go Back",
                        modifier = Modifier.size(48.dp),
                    )
                }
            },
            alignRightContent = {},
        )
    }
}
