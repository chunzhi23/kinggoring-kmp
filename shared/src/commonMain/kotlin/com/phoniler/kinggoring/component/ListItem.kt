package com.phoniler.kinggoring.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun SidebarItem(
    titleText: String,
    titleFontSize: TextUnit = 16.sp,
    subtitleText: String = "",
    subtitleFontSize: TextUnit = 0.sp,
    onItemClick: () -> Unit = {},
) {
    Box(modifier = Modifier.clickable { onItemClick() }) {
        Column {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = titleText, fontSize = titleFontSize)
                if (subtitleText.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = subtitleText, fontSize = subtitleFontSize)
                }
            }
            Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color(0xFFD3D3D3)))
        }
    }
}

@Composable
fun NotificationItem(
    text: String,
    textFontSize: TextUnit = 16.sp,
    time: Instant,
    timeFontSize: TextUnit = 14.sp,
) {
    val timeZone = TimeZone.currentSystemDefault()
    val currentDateTime = Clock.System.now().toLocalDateTime(timeZone)
    val notificationDateTime = time.toLocalDateTime(timeZone)

    val formattedTime =
        remember(notificationDateTime) {
            when {
                notificationDateTime.date == currentDateTime.date -> notificationDateTime.time.toString().take(5) // HH:mm format
                notificationDateTime.date.year == currentDateTime.date.year -> {
                    "${notificationDateTime.monthNumber}/${notificationDateTime.dayOfMonth} ${notificationDateTime.time.toString().take(5)}" // MM/dd HH:mm
                }
                else -> {
                    "${notificationDateTime.year}/${notificationDateTime.monthNumber}/${notificationDateTime.dayOfMonth} ${notificationDateTime.time.toString().take(
                        5,
                    )}" // yyyy/MM/dd HH:mm
                }
            }
        }

    Column {
        Row(modifier = Modifier.padding(16.dp)) {
            Text(text, fontSize = textFontSize, modifier = Modifier.weight(1f))
            Text(formattedTime, fontSize = timeFontSize)
        }
        Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(Color(0xFFD3D3D3)))
    }
}

@Composable
fun deviceSettingsItem(
    name: String,
    icon: ImageVector,
    checked: Boolean,
    onCheckedChange: () -> Unit,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = name,
                tint = Color.Black,
                modifier = Modifier.size(36.dp),
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = name, fontSize = 18.sp)
        }
        Switch(
            checked = checked,
            onCheckedChange = { onCheckedChange() },
        )
    }
}
