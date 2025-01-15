package com.phoniler.kinggoring.view

import androidx.compose.ui.Modifier
import com.phoniler.kinggoring.PlatformStorableImage
import com.phoniler.kinggoring.model.PictureData

@Composable
actual fun CameraView(
    modifier: Modifier,
    onCapture: (picture: PictureData.Camera, image: PlatformStorableImage) -> Unit,
) {
}
