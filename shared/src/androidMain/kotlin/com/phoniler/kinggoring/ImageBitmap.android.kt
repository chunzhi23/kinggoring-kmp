package com.phoniler.kinggoring

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

actual fun ByteArray.toImageBitmap(): ImageBitmap = toAndroidBitmap().asImageBitmap()

fun ByteArray.toAndroidBitmap(): Bitmap = BitmapFactory.decodeByteArray(this, 0, size)
