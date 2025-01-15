package com.phoniler.kinggoring

import kotlinx.coroutines.CoroutineDispatcher

expect class PlatformStorableImage

expect fun createUUID(): String

expect val ioDispatcher: CoroutineDispatcher
