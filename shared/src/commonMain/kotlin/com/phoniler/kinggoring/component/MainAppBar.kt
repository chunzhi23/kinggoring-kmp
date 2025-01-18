package com.phoniler.kinggoring.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.phoniler.kinggoring.icon.IconChat
import com.phoniler.kinggoring.icon.IconHome
import com.phoniler.kinggoring.icon.IconMenu
import com.phoniler.kinggoring.icon.IconNotifications
import com.phoniler.kinggoring.icon.IconPerson
import com.phoniler.kinggoring.type.NavType

@Composable
fun MainTopAppBar(
    title: String,
    navValue: NavType,
    onMenuClick: () -> Unit,
    onNoticeClick: () -> Unit,
    isBottomDivider: Boolean = true,
) {
    Column {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .heightIn(min = 40.dp)
                    .background(Color.White),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = onMenuClick) {
                Icon(
                    imageVector = IconMenu,
                    contentDescription = "Menu",
                    tint = Color.Black,
                    modifier = Modifier.size(28.dp),
                )
            }
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = title, fontSize = 16.sp)
            }
            IconButton(
                onClick = onNoticeClick,
            ) {
                Icon(
                    imageVector = IconNotifications,
                    contentDescription = "Notifications",
                    tint = if (navValue == NavType.NOTIFICATION) Color(0xFF007AFF) else Color.Black,
                    modifier = Modifier.size(28.dp),
                )
            }
        }
        if (isBottomDivider) {
            HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = Color(0xFFD3D3D3), thickness = 1.dp)
        }
    }
}

@Composable
fun MainBottomNavigationBar(
    navValue: NavType,
    onNavClick: (target: NavType) -> Unit,
) {
    Box(contentAlignment = Alignment.BottomEnd) {
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(61.dp)
                    .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
                    .background(Color(0xFFD3D3D3)),
        )
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 1.dp)
                    .height(60.dp)
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .background(Color.White),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            NavType.entries.forEach { navItem ->
                if (navItem != NavType.NOTIFICATION) {
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = { onNavClick(navItem) },
                        shape = RoundedCornerShape(24.dp),
                        colors =
                            ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                                contentColor = if (navValue == navItem) Color(0xFF007AFF) else Color.Black,
                            ),
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            when (navItem) {
                                NavType.HOME -> {
                                    Icon(
                                        imageVector = IconHome,
                                        contentDescription = "Home",
                                        modifier = Modifier.size(24.dp),
                                    )
                                    Text("홈")
                                }

                                NavType.CHATBOT -> {
                                    Icon(
                                        imageVector = IconChat,
                                        contentDescription = "Chat",
                                        modifier = Modifier.size(24.dp),
                                    )
                                    Text("채팅")
                                }

                                NavType.MYPAGE -> {
                                    Icon(
                                        imageVector = IconPerson,
                                        contentDescription = "My page",
                                        modifier = Modifier.size(24.dp),
                                    )
                                    Text("프로필")
                                }

                                else -> {}
                            }
                        }
                    }
                }
            }
        }
    }
}
