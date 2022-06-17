package com.godamy.marvelcompose.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.godamy.marvelcompose.ui.navigation.AppBottomNavigation
import com.godamy.marvelcompose.ui.navigation.DrawerContent
import com.godamy.marvelcompose.ui.navigation.NavItem
import com.godamy.marvelcompose.ui.navigation.Navigation
import com.godamy.marvelcompose.ui.screen.main.MainAppBar
import com.godamy.marvelcompose.ui.screen.main.MarvelScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun MarvelApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ""
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    val bottomNavOptions = listOf(NavItem.COMICS, NavItem.CHARACTERS, NavItem.EVENTS)
    val drawerOptions = listOf(NavItem.HOME, NavItem.SETTINGS)
    val showBottomNavigation = bottomNavOptions.any{ currentRoute.contains(it.navCommand.feature.route)}

    val drawerSelectedIndex = if(showBottomNavigation) {
        drawerOptions.indexOf(NavItem.HOME)
    } else {
        drawerOptions.indexOfFirst { it.navCommand.route == currentRoute }
    }

    MarvelScreen {
        Scaffold(
            topBar = {
                MainAppBar(scaffoldState)
            },
            bottomBar = {
                if (showBottomNavigation) {
                    AppBottomNavigation(
                        currentRoute = currentRoute,
                        onNavItemClick = {
                            navController.navigatePopUpToStartDestination(it.navCommand.route)
                        },
                        bottomNavOptions
                    )
                }
            },
            drawerContent = {
                DrawerContent(
                    onOptionClick = {
                        coroutineScope.launch {
                            coroutineScope.launch { scaffoldState.drawerState.close() }
                            navController.navigate(it.navCommand.route)
                        }
                    },
                    selectedIndex = drawerSelectedIndex,
                    drawerOptions
                )
            },
            scaffoldState = scaffoldState
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Navigation(navController)
            }
        }
    }
}

private fun NavHostController.navigatePopUpToStartDestination(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
