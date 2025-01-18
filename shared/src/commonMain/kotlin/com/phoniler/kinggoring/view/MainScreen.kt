package com.phoniler.kinggoring.view

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.phoniler.kinggoring.component.MainBottomNavigationBar
import com.phoniler.kinggoring.component.MainTopAppBar
import com.phoniler.kinggoring.component.NotificationItem
import com.phoniler.kinggoring.component.SidebarItem
import com.phoniler.kinggoring.icon.IconChat
import com.phoniler.kinggoring.icon.IconPerson
import com.phoniler.kinggoring.type.AnalList
import com.phoniler.kinggoring.type.AnalType
import com.phoniler.kinggoring.type.NavType
import kotlinx.datetime.Instant

@Composable
fun MainScreen(
    mainView: NavType,
    onAnalClick: (target: AnalType) -> Unit,
) {
    val navState = remember { mutableStateOf(mainView) }
    val previousNavState = remember { mutableStateOf<NavType?>(null) }
    val isPanelOpen = remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Column {
            MainTopAppBar(
                title = "킹고링",
                navValue = navState.value,
                onMenuClick = { isPanelOpen.value = !isPanelOpen.value },
                onNoticeClick = {
                    if (navState.value == NavType.NOTIFICATION) {
                        navState.value = previousNavState.value ?: NavType.HOME
                        previousNavState.value = null
                    } else {
                        previousNavState.value = navState.value
                        navState.value = NavType.NOTIFICATION
                    }
                    isPanelOpen.value = false
                },
            )
            Box(modifier = Modifier.weight(1f)) {
                ContentView(
                    navValue = navState.value,
                    isPanelValue = isPanelOpen.value,
                    onDarkenClick = { isPanelOpen.value = false },
                    onAnalClick = onAnalClick,
                )
            }
            Spacer(modifier = Modifier.height(61.dp))
        }
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
        ) {
            MainBottomNavigationBar(
                navValue = navState.value,
                onNavClick = { target: NavType ->
                    navState.value = target
                    isPanelOpen.value = false
                    previousNavState.value = null
                },
            )
        }
    }
}

@Composable
private fun ContentView(
    navValue: NavType,
    isPanelValue: Boolean,
    onDarkenClick: () -> Unit,
    onAnalClick: (target: AnalType) -> Unit,
) {
    when (navValue) {
        NavType.HOME -> HomeView(onAnalClick)
        NavType.CHATBOT -> ChatView()
        NavType.MYPAGE -> MyPageView()
        NavType.NOTIFICATION -> NotificationView()
    }

    val slideOffset by animateDpAsState(
        targetValue = if (isPanelValue) 0.dp else (-250).dp,
        animationSpec = tween(durationMillis = 300),
    )

    Box(
        modifier =
            Modifier
                .fillMaxHeight()
                .width(250.dp)
                .offset(x = slideOffset)
                .background(Color.White)
                .zIndex(1f)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null,
                    onClick = { },
                ),
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            SidebarItem(
                titleText = "성균관님, 반갑습니다",
                titleFontSize = 18.sp,
                subtitleText = "다음으로 할 일: 인슐린 복용",
                subtitleFontSize = 14.sp,
            )
            SidebarItem("식사 시간 설정")
            SidebarItem("취침 시간 설정")
            SidebarItem("복약 설정")
            SidebarItem("주입 인슐린 설정")
        }
    }

    if (isPanelValue) {
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null,
                    ) { onDarkenClick() }
                    .zIndex(0.5f),
        )
    }
}

@Composable
private fun HomeView(onAnalClick: (target: AnalType) -> Unit) {
    val itemWidth = 120.dp
    val itemSpacing = 24.dp

    val screenWidth = getScreenWidth()

    val totalSpacing = itemSpacing * 2
    val availableWidth = screenWidth - totalSpacing
    val itemWidthWithSpacing = itemWidth + itemSpacing
    val columns = (availableWidth / itemWidthWithSpacing).toInt().coerceAtLeast(1)

    val items: List<AnalType> =
        listOf(
            AnalType.INSULIN,
            AnalType.BLOOD_SUGAR,
            AnalType.EXERCISE,
            AnalType.MEAL,
        ).plus(
            AnalType.ADD,
        )

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(48.dp))

        val filteredItems = AnalList.filter { analItem -> analItem.type in items }

        for (row in filteredItems.chunked(columns)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(itemSpacing, Alignment.CenterHorizontally),
            ) {
                for ((text, type, icon) in row) {
                    Button(
                        onClick = { onAnalClick(type) },
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
                            Spacer(modifier = Modifier.height(5.dp))
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

@Composable
expect fun getScreenWidth(): Dp

@Composable
private fun ChatView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = IconChat,
                contentDescription = "Chat",
                modifier = Modifier.size(60.dp),
            )
            Text("채팅")
        }
    }
}

@Composable
private fun MyPageView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = IconPerson,
                contentDescription = "Profile",
                modifier = Modifier.size(60.dp),
            )
            Text("프로필")
        }
    }
}

@Composable
private fun NotificationView() {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        NotificationItem("인슐린을 주입하세요", time = Instant.parse("2025-01-17T09:30:00Z"))
        NotificationItem("약(리스프로)를 복용하세요", time = Instant.parse("2025-01-16T12:00:00Z"))
        NotificationItem("혈당을 체크하세요", time = Instant.parse("2025-01-01T08:00:00Z"))
        NotificationItem("식사 사진이 보호자에게 전송되었습니다", time = Instant.parse("2024-12-31T20:45:00Z"))
        NotificationItem("혈당을 체크하세요", time = Instant.parse("2024-01-17T23:59:59Z"))
        NotificationItem("인슐린을 주입하세요", time = Instant.parse("2023-07-15T14:00:00Z"))
    }
}
