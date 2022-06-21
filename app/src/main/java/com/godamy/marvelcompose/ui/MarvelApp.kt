package com.godamy.marvelcompose.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.godamy.marvelcompose.ui.navigation.AppBottomNavigation
import com.godamy.marvelcompose.ui.navigation.DrawerContent
import com.godamy.marvelcompose.ui.navigation.Navigation
import com.godamy.marvelcompose.ui.screen.main.MainAppBar
import com.godamy.marvelcompose.ui.screen.main.MarvelScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun MarvelApp() {
    val marvelAppState = rememberMarvelAppState()

    MarvelScreen {
        Scaffold(
            topBar = {
                MainAppBar(marvelAppState::onMenuClick)
            },
            bottomBar = {
                if (marvelAppState.showBottomNavigation) {
                    AppBottomNavigation(
                        currentRoute = marvelAppState.currentRoute,
                        onNavItemClick = marvelAppState::onNavItemClick,
                        bottomNavOptions = MarvelAppState.BOTTOM_NAV_OPTIONS
                    )
                }
            },
            drawerContent = {
                DrawerContent(
                    onOptionClick = marvelAppState::onDrawerOptionClick,
                    selectedIndex = marvelAppState.drawerSelectedIndex,
                    drawerOptions = MarvelAppState.DRAWER_OPTIONS
                )
            },
            scaffoldState = marvelAppState.scaffoldState
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Navigation(marvelAppState.navController)
            }
        }
    }
}
