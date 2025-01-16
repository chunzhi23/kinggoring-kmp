package com.phoniler.kinggoring.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phoniler.kinggoring.icon.IconAdd
import com.phoniler.kinggoring.icon.IconExercise
import com.phoniler.kinggoring.icon.IconFinance
import com.phoniler.kinggoring.icon.IconMenu
import com.phoniler.kinggoring.icon.IconSyringe

@Composable
actual fun HomeView() {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

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
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(itemSpacing, Alignment.CenterHorizontally),
            ) {
                for ((text, icon) in row) {
                    Button(
                        onClick = { /* Handle click event for this item */ },
                        modifier = Modifier.size(itemWidth),
                        contentPadding = PaddingValues(16.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors =
                            ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFF5F5F5),
                                contentColor = Color.Black,
                            ),
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
        Spacer(modifier = Modifier.height(24.dp))
    }
}
