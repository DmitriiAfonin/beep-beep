package org.thechance.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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