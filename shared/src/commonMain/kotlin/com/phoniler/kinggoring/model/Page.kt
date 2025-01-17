package com.phoniler.kinggoring.model

import com.phoniler.kinggoring.type.AnalType

interface Page

expect class TaggedPage() : Page

expect class CameraPage() : Page

expect class MainPage() : Page

expect class AnalysisPage(
    analView: AnalType,
) : Page {
    val analView: AnalType
}
