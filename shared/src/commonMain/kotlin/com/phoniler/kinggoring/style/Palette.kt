package com.phoniler.kinggoring.style

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun KinggoRingTheme(content: @Composable () -> Unit) {
    isSystemInDarkTheme()
    MaterialTheme {
        ProvideTextStyle(LocalTextStyle.current.copy(letterSpacing = 0.sp)) {
            content()
        }
    }
}
