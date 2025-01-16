package com.phoniler.kinggoring.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.phoniler.kinggoring.icon.IconBarChart
import com.phoniler.kinggoring.icon.IconExercise
import com.phoniler.kinggoring.icon.IconForkSpoon
import com.phoniler.kinggoring.icon.IconSyringe

@Composable
fun TaggedScreen(onMeal: () -> Unit) {
    val isBloodSugar = remember { mutableStateOf(false) }

    Box(
        modifier =
            Modifier.fillMaxSize().background(Color.Black).clickable {
                isBloodSugar.value = false
            },
        contentAlignment = Alignment.Center,
    ) {
        if (!isBloodSugar.value) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    GridItem(
                        icon = IconSyringe,
                        label = "인슐린",
                        onClickAction = {},
                    )
                    GridItem(
                        icon = IconBarChart,
                        label = "혈당 측정",
                        onClickAction = { isBloodSugar.value = true },
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    GridItem(
                        icon = IconExercise,
                        label = "운동",
                        onClickAction = {},
                    )
                    GridItem(
                        icon = IconForkSpoon,
                        label = "식사",
                        onClickAction = onMeal,
                    )
                }
            }
        } else {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text("혈당 측정", color = Color.White)
                BloodSugarButton("고혈당", Color.Yellow, onClick = { /* 고혈당 */ })
                BloodSugarButton("정상", Color.Green, onClick = { /* 정상 */ })
                BloodSugarButton("저혈당", Color.Red, onClick = { /* 저혈당 */ })
            }
        }
    }
}

@Composable
fun GridItem(
    icon: ImageVector,
    label: String,
    onClickAction: () -> Unit,
) {
    Button(
        modifier = Modifier.size(100.dp),
        shape = RoundedCornerShape(16.dp),
        colors =
            ButtonColors(
                contentColor = Color.Black,
                containerColor = Color.White,
                disabledContentColor = Color.Gray,
                disabledContainerColor = Color.Gray,
            ),
        onClick = onClickAction,
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

@Composable
fun BloodSugarButton(
    label: String,
    backgroundColor: Color,
    onClick: () -> Unit,
) {
    Button(
        modifier = Modifier.fillMaxWidth(0.6f).size(50.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
        onClick = onClick,
    ) {
        Text(
            text = label,
            color = Color.Black,
        )
    }
}
