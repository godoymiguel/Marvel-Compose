package com.godamy.marvelcompose.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.godamy.marvelcompose.ui.screen.detail.ComicDetailScreen
import com.godamy.marvelcompose.ui.screen.main.ComicsScreen

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Feature.COMICS.route
    ) {
        comicsNav(navController)
        //charactersNav(navController)
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
private fun NavGraphBuilder.comicsNav(navController: NavHostController) {

    navigation(
        startDestination = NavItem.ContentType(Feature.COMICS).route,
        route = Feature.COMICS.route
    ) {
        composable(NavItem.ContentType(Feature.COMICS)) {
            ComicsScreen(onClick = {
                navController.navigate(NavItem.ContentDetail(Feature.COMICS).createRoute(it.id))
            })
        }
        composable(NavItem.ContentDetail(Feature.COMICS)) {
            ComicDetailScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                comicId = it.findArg(NavArg.ItemId)
            )
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