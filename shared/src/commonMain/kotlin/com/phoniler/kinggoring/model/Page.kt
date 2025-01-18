package com.phoniler.kinggoring.model

import com.phoniler.kinggoring.type.AnalType
import com.phoniler.kinggoring.type.NavType

interface Page

expect class TaggedPage() : Page

expect class CameraPage() : Page

expect class MainPage(
    mainView: NavType = NavType.HOME,
) : Page {
    val mainView: NavType
}

expect class AnalysisPage(
    analView: AnalType,
) : Page {
    val analView: AnalType
}
