package com.phoniler.kinggoring.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.phoniler.kinggoring.component.MainBottomNavigationBar
import com.phoniler.kinggoring.component.MainTopAppBar
import com.phoniler.kinggoring.icon.IconChat
import com.phoniler.kinggoring.icon.IconPerson
import com.phoniler.kinggoring.type.NavType

@Composable
fun MainScreen() {
    val navItemState = remember { mutableStateOf(NavType.HOME) }
    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Column {
            MainTopAppBar("킹고링", navState = navItemState)
            Box(modifier = Modifier.weight(1f)) {
                ContentView(navItemState)
            }
            MainBottomNavigationBar(navItemState)
        }
    }
}

@Composable
fun ContentView(navState: MutableState<NavType>) {
    when (navState.value) {
        NavType.HOME -> HomeView()
        NavType.CHATBOT -> ChatView()
        NavType.MYPAGE -> MyPageView()
        NavType.NOTIFICATION -> TODO()
    }
}

@Composable
expect fun HomeView(): Unit

@Composable
fun ChatView() {
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
fun MyPageView() {
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
fun NotificationView() {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {}
}
