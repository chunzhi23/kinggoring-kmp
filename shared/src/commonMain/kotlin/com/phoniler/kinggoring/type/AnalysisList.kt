package com.phoniler.kinggoring.type

import androidx.compose.ui.graphics.vector.ImageVector
import com.phoniler.kinggoring.icon.IconAdd
import com.phoniler.kinggoring.icon.IconExercise
import com.phoniler.kinggoring.icon.IconFinance
import com.phoniler.kinggoring.icon.IconForkSpoon
import com.phoniler.kinggoring.icon.IconSyringe

enum class AnalType {
    INSULIN,
    BLOOD_SUGAR,
    EXERCISE,
    MEAL,
    ADD,
}

data class AnalItem(
    val title: String,
    val type: AnalType,
    val icon: ImageVector,
)

val AnalList: List<AnalItem> =
    listOf(
        AnalItem("인슐린", AnalType.INSULIN, IconSyringe),
        AnalItem("혈당 측정", AnalType.BLOOD_SUGAR, IconFinance),
        AnalItem("운동", AnalType.EXERCISE, IconExercise),
        AnalItem("식사", AnalType.MEAL, IconForkSpoon),
        AnalItem("추가", AnalType.ADD, IconAdd),
    )
