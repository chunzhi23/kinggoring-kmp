package com.phoniler.kinggoring.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.phoniler.kinggoring.PlatformStorableImage
import com.phoniler.kinggoring.model.PictureData

@Composable
expect fun CameraView(
    modifier: Modifier,
    onCapture: (picture: PictureData.Camera, image: PlatformStorableImage) -> Unit,
)
