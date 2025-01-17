package com.phoniler.kinggoring.model

import com.phoniler.kinggoring.type.AnalType

actual class TaggedPage actual constructor() : Page

actual class CameraPage actual constructor() : Page

actual class MainPage actual constructor() : Page

actual class AnalysisPage actual constructor(
    actual val analView: AnalType,
) : Page
