package com.phoniler.kinggoring.model

import android.os.Parcelable
import com.phoniler.kinggoring.type.AnalType
import com.phoniler.kinggoring.type.NavType
import kotlinx.parcelize.Parcelize

@Parcelize
actual class TaggedPage actual constructor() :
    Page,
    Parcelable

@Parcelize
actual class CameraPage actual constructor() :
    Page,
    Parcelable

@Parcelize
actual class MainPage actual constructor(
    actual val mainView: NavType,
) : Page,
    Parcelable

@Parcelize
actual class AnalysisPage actual constructor(
    actual val analView: AnalType,
) : Page,
    Parcelable
