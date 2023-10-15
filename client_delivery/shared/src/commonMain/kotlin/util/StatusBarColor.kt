package util

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import io.ktor.client.engine.HttpClientEngine


expect class PlatformContext

@Composable
expect fun getPlatformContext(): PlatformContext

@Composable
expect fun SetInsetsController(isDark: Boolean)

@Composable
expect fun getNavigationBarPadding(): PaddingValues

@Composable
expect fun getStatusBarPadding(): PaddingValues


expect fun getEngine(): HttpClientEngine
