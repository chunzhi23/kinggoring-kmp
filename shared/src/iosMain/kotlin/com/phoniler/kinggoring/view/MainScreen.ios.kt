package com.phoniler.kinggoring.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phoniler.kinggoring.icon.IconAdd
import com.phoniler.kinggoring.icon.IconExercise
import com.phoniler.kinggoring.icon.IconFinance
import com.phoniler.kinggoring.icon.IconMenu
import com.phoniler.kinggoring.icon.IconSyringe
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.Foundation.NSLog
import platform.UIKit.UIScreen

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun HomeView() {
    val screenWidth =
        UIScreen.mainScreen.bounds
            .useContents { size.width }
            .dp
    val itemWidth = 120.dp
    val itemSpacing = 24.dp

    val totalSpacing = itemSpacing * 2
    val availableWidth = screenWidth - totalSpacing
    val itemWidthWithSpacing = itemWidth + itemSpacing
    val columns = (availableWidth / itemWidthWithSpacing).toInt().coerceAtLeast(1)

    val items =
        listOf(
            "인슐린" to IconSyringe,
            "혈당측정" to IconFinance,
            "운동" to IconExercise,
            "식사" to IconMenu,
            "추가" to IconAdd,
        )

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(48.dp))

        for (row in items.chunked(columns)) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(itemSpacing),
            ) {
                for ((text, icon) in row) {
                    Button(
                        onClick = { NSLog("Clicked: $text") },
                        modifier = Modifier.size(itemWidth),
                        shape = RoundedCornerShape(10.dp),
                        contentPadding = PaddingValues(16.dp),
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Icon(
                                imageVector = icon,
                                contentDescription = text,
                                modifier = Modifier.size(40.dp),
                            )
                            Text(text = text, fontSize = 16.sp)
                        }
                    }
                }

                if (row.size < columns) {
                    repeat(columns - row.size) {
                        Spacer(modifier = Modifier.size(itemWidth))
                    }
                }
            }
            Spacer(modifier = Modifier.height(itemSpacing))
        }
    }
}
