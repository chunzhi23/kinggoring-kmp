package com.phoniler.kinggoring

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phoniler.kinggoring.icon.IconOutlinedBarChart
import com.phoniler.kinggoring.icon.IconOutlinedExercise
import com.phoniler.kinggoring.icon.IconOutlinedForkSpoon
import com.phoniler.kinggoring.icon.IconOutlinedSyringe
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val isDarkTheme = isSystemInDarkTheme()
    val supportsDynamicColor = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

    val lightColorScheme = lightColorScheme(primary = Color(0xFF1EB980))

    val darkColorScheme = darkColorScheme(primary = Color(0xFF66ffc7))

    val colorScheme =
        when {
            supportsDynamicColor && isDarkTheme -> {
                dynamicDarkColorScheme(LocalContext.current)
            }
            supportsDynamicColor && !isDarkTheme -> {
                dynamicLightColorScheme(LocalContext.current)
            }
            isDarkTheme -> darkColorScheme
            else -> lightColorScheme
        }

    val typography =
        Typography(
            displaySmall = TextStyle(fontWeight = FontWeight.W100, fontSize = 96.sp),
            labelLarge = TextStyle(fontWeight = FontWeight.W600, fontSize = 14.sp),
        )

    val shapes = Shapes(extraSmall = RoundedCornerShape(3.0.dp), small = RoundedCornerShape(6.0.dp))

    MaterialTheme(colorScheme = colorScheme, typography = typography, shapes = shapes) {
        Surface(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(Color.Black),
        ) {
            GridContent()
        }
    }
}

@Composable
fun GridContent() {
    // Centering the grid
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Rows of 2 items each
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                GridItem(icon = IconOutlinedSyringe, label = "인슐린")
                GridItem(icon = IconOutlinedBarChart, label = "혈당 측정")
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                GridItem(icon = IconOutlinedExercise, label = "운동")
                GridItem(icon = IconOutlinedForkSpoon, label = "식사")
            }
        }
    }
}

@Composable
fun GridItem(
    icon: ImageVector,
    label: String,
) {
    Box(
        modifier =
            Modifier
                .size(100.dp)
                .background(Color.White),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "Syringe Icon",
                modifier = Modifier.size(48.dp),
            )
            Text(
                text = label,
                color = Color.Black,
            )
        }
    }
}
