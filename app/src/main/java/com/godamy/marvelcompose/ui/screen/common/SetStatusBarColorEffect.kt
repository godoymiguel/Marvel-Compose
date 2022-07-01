package com.godamy.marvelcompose.ui.screen.common

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SetStatusBarColorEffect(
    systemUiController: SystemUiController = rememberSystemUiController(),
    primaryVariant: Color = MaterialTheme.colors.primaryVariant
) {
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = primaryVariant
        )
    }
}
