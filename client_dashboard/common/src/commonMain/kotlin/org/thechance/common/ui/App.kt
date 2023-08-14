package org.thechance.common.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.beepbeep.designSystem.ui.composable.BpNavigationBarItem
import com.beepbeep.designSystem.ui.composable.BpSideBar
import org.thechance.common.di.initKoin
import org.thechance.common.ui.screen.login.LoginScreen
import org.thechance.common.ui.screen.main.OverviewTab
import org.thechance.common.ui.screen.main.RestaurantsTab
import org.thechance.common.ui.screen.main.TaxisTab
import org.thechance.common.ui.screen.main.UsersTab


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun App() {
    initKoin()
    Navigator(LoginScreen) {
        SlideTransition(it)
    }

}

object HomeContent : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        TabNavigator(OverviewTab) { navigator ->
            Scaffold {
                Row {
                    BpSideBar(
                        painter = painterResource("ic_logo_small.svg"),
                        modifier = Modifier.width(100.dp).fillMaxHeight(),
                    ) {
                        TabNavigationItem(OverviewTab)
                        TabNavigationItem(TaxisTab)
                        TabNavigationItem(RestaurantsTab)
                        TabNavigationItem(UsersTab)
                    }
                    CurrentTab()
                }
            }
        }
    }
}

@Composable
fun ColumnScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    BpNavigationBarItem(
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = { tab.options.icon?.let { Icon(painter = it, contentDescription = tab.title) } },
        label = { Text(tab.options.title) },
        alwaysShowLabel = false,
    )
}