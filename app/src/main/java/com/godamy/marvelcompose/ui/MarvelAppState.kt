package com.godamy.marvelcompose.ui

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.godamy.marvelcompose.ui.navigation.NavItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MarvelAppState(
    val navController: NavHostController,
    val scaffoldState: ScaffoldState,
    private val coroutineScope: CoroutineScope
) {

    companion object {
        val BOTTOM_NAV_OPTIONS = listOf(NavItem.COMICS, NavItem.CHARACTERS, NavItem.EVENTS)
        val DRAWER_OPTIONS = listOf(NavItem.HOME, NavItem.SETTINGS)
    }

    val currentRoute: String
        @Composable get() =
            navController.currentBackStackEntryAsState().value?.destination?.route
                ?: ""

    val showBottomNavigation: Boolean
        @Composable get() =
            BOTTOM_NAV_OPTIONS.any { currentRoute.contains(it.navCommand.feature.route) }


    val drawerSelectedIndex: Int
        @Composable get() = if (showBottomNavigation) {
            DRAWER_OPTIONS.indexOf(NavItem.HOME)
        } else {
            DRAWER_OPTIONS.indexOfFirst { it.navCommand.route == currentRoute }
        }

    fun onNavItemClick(navItem: NavItem) {
        navController.navigatePopUpToStartDestination(navItem.navCommand.route)
    }

    fun onMenuClick() {
        coroutineScope.launch { scaffoldState.drawerState.open() }
    }

    fun onDrawerOptionClick(navItem: NavItem) {
        coroutineScope.launch { scaffoldState.drawerState.close() }
        onNavItemClick(navItem)
    }

    // Extensions
    private fun NavHostController.navigatePopUpToStartDestination(route: String) {
        navigate(route) {
            popUpTo(graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}
