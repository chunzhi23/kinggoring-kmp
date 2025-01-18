package com.phoniler.kinggoring.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phoniler.kinggoring.icon.IconArrowBack
import com.phoniler.kinggoring.icon.IconChat

@Composable
fun AnalTopAppBar(
    title: String,
    onBackClick: () -> Unit,
) {
    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Color.White),
    ) {
        Text(
            text = title,
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center,
        )
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.align(Alignment.CenterStart),
        ) {
            Icon(
                imageVector = IconArrowBack,
                contentDescription = "Back",
                tint = Color.Black,
                modifier = Modifier.size(28.dp),
            )
        }
    }
}

@Composable
fun AnalFloatingActionButton(onClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier =
                Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
                    .size(70.dp)
                    .shadow(
                        elevation = 10.dp,
                        shape = CircleShape,
                        clip = false,
                    ).clip(CircleShape)
                    .background(Color(0xFF007AFF))
                    .clickable(onClick = onClick),
            contentAlignment = Alignment.Center,
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = IconChat,
                    contentDescription = "Chatbot",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp),
                )
                Text(
                    text = "채팅",
                    fontSize = 14.sp,
                    color = Color.White,
                )
            }
        }
    }
}
