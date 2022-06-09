package com.godamy.marvelcompose.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.godamy.marvelcompose.ui.screens.comics.ComicsScreen
import com.godamy.marvelcompose.ui.screens.comics.detail.ComicDetailScreen

@ExperimentalFoundationApi
@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavItem.Comics.route
    ) {
        composable(NavItem.Comics) {
            ComicsScreen(onClick = {
                navController.navigate(NavItem.ComicDetail.createRoute(it.id))
            })
        }
        composable(NavItem.ComicDetail) {
            ComicDetailScreen(it.findArg<Int>(NavArg.ItemId))
        }
    }
}

private fun NavGraphBuilder.composable(
    navItem: NavItem,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navItem.route,
        arguments = navItem.args
    ) {
        content(it)
    }
}

private inline fun <reified T> NavBackStackEntry.findArg(arg: NavArg): T {
    val value = arguments?.get(arg.key)
    requireNotNull(value)
    return value as T
}