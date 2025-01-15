package com.phoniler.kinggoring.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.phoniler.kinggoring.icon.IconOutlinedBarChart
import com.phoniler.kinggoring.icon.IconOutlinedExercise
import com.phoniler.kinggoring.icon.IconOutlinedForkSpoon
import com.phoniler.kinggoring.icon.IconOutlinedSyringe

@Composable
fun TaggedScreen(onClickMeal: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                GridItem(icon = IconOutlinedSyringe, label = "인슐린", onClickAction = {})
                GridItem(icon = IconOutlinedBarChart, label = "혈당 측정", onClickAction = {})
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                GridItem(icon = IconOutlinedExercise, label = "운동", onClickAction = {})
                GridItem(icon = IconOutlinedForkSpoon, label = "식사", onClickAction = onClickMeal)
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
