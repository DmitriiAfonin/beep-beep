package org.thechance.common.presentation.app

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.beepbeep.designSystem.ui.theme.BpTheme
import org.thechance.common.presentation.login.LoginScreen
import org.thechance.common.presentation.main.MainContainer
import org.thechance.common.presentation.main.OverviewTab
import org.thechance.common.presentation.main.RestaurantsTab
import org.thechance.common.presentation.main.TaxisTab
import org.thechance.common.presentation.resources.ProvideResources

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun App() {

//    val appScreenModel by inject<AppScreenModel>(AppScreenModel::class.java)
//    val themeMode by appScreenModel.state.collectAsState()
    BpTheme{
        ProvideResources {
            Navigator(MainContainer) {
                SlideTransition(it)
            }
        }
    }
}
