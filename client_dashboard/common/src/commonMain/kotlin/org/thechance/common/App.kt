package org.thechance.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import org.thechance.common.di.initKoin
import org.thechance.common.ui.screen.SecondScreen
import org.thechance.common.ui.screen.ThirdScreen
import org.thechance.common.ui.screen.first.FirstScreen

@Composable
fun App() {
    initKoin()
    TabNavigator(FirstScreen) {
        Scaffold(
            bottomBar = { BottomBar() }
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                CurrentTab()
                Column(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(16.dp),
                ) {
                    LocalNavigator.currentOrThrow.items.forEach { screen ->
                        Text(
                            text = "KEY: ${screen.key}",
                            fontSize = 16.sp,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BottomBar() {
    BottomNavigation {
        TabNavigationItem(FirstScreen)
        TabNavigationItem(SecondScreen("Favorites"))
        TabNavigationItem(ThirdScreen)
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current

    BottomNavigationItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = {
            Icon(
                painter = tab.options.icon ?: return@BottomNavigationItem,
                contentDescription = tab.options.title,
                tint = if (tabNavigator.current == tab) Color.White else Color.White.copy(alpha = 0.5f),
            )
        },
    )
}