package com.phoniler.kinggoring

import androidx.compose.ui.graphics.ImageBitmap
import kotlinx.coroutines.Dispatchers
import java.util.UUID

class AndroidStorableImage(
    val imageBitmap: ImageBitmap,
)

actual fun createUUID(): String = UUID.randomUUID().toString()

actual typealias PlatformStorableImage = AndroidStorableImage

actual val ioDispatcher = Dispatchers.IO
