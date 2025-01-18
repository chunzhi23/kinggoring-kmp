package com.phoniler.kinggoring.model

import com.phoniler.kinggoring.type.AnalType
import com.phoniler.kinggoring.type.NavType

actual class TaggedPage actual constructor() : Page

actual class CameraPage actual constructor() : Page

actual class MainPage actual constructor(
    actual val mainView: NavType,
) : Page

actual class AnalysisPage actual constructor(
    actual val analView: AnalType,
) : Page
