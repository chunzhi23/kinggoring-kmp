package com.phoniler.kinggoring.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.phoniler.kinggoring.component.AnalFloatingActionButton
import com.phoniler.kinggoring.component.AnalTopAppBar
import com.phoniler.kinggoring.type.AnalList
import com.phoniler.kinggoring.type.AnalType

@Composable
fun AnalysisScreen(
    analView: AnalType,
    onBackClick: () -> Unit,
    onChatClick: () -> Unit,
) {
    val selectedTab = remember { mutableStateOf(0) }
    val tabTitles = listOf("오늘", "지난 7일", "지난 30일")

    val analItem = AnalList.find { analItem -> analItem.type == analView }
    val analName = analItem?.title

    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        if (analName == null) {
            Text("예기치 못한 오류가 발생했습니다.")
        } else {
            ContentView(
                analName,
                onBackClick,
                selectedTabValue = selectedTab.value,
                onTabClick = { idx -> selectedTab.value = idx },
                tabTitles,
            )
        }
        Box(modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter)) {
            AnalFloatingActionButton(onClick = onChatClick)
        }
    }
}

@Composable
private fun ContentView(
    analName: String,
    onBackClick: () -> Unit,
    selectedTabValue: Int,
    onTabClick: (idx: Int) -> Unit,
    tabTitles: List<String>,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Column {
            AnalTopAppBar(analName) { onBackClick() }
            TabRow(
                selectedTabIndex = selectedTabValue,
                containerColor = Color.White,
                contentColor = Color.Black,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabValue]),
                        color = Color(0xFF007AFF),
                    )
                },
            ) {
                tabTitles.forEachIndexed { idx, title ->
                    Tab(
                        selected = selectedTabValue == idx,
                        onClick = { onTabClick(idx) },
                        text = {
                            Text(
                                text = title,
                                color = if (selectedTabValue == idx) Color.Black else Color.Gray,
                            )
                        },
                    )
                }
            }
        }
        when (selectedTabValue) {
            0 -> TodayView()
            1 -> ThisWeekView()
            2 -> ThisMonthView()
        }
    }
}

@Composable
fun TodayView() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("오늘")
    }
}

@Composable
fun ThisWeekView() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("지난 7일")
    }
}

@Composable
fun ThisMonthView() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("지난 30일")
    }
}
