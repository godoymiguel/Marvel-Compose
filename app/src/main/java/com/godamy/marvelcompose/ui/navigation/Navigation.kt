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
import com.godamy.marvelcompose.ui.screen.character.CharacterDetailScreen
import com.godamy.marvelcompose.ui.screen.character.CharactersScreen
import com.godamy.marvelcompose.ui.screen.comic.ComicDetailScreen
import com.godamy.marvelcompose.ui.screen.comic.ComicsScreen
import com.godamy.marvelcompose.ui.screen.event.EventDetailScreen
import com.godamy.marvelcompose.ui.screen.event.EventsScreen

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Feature.COMICS.route
    ) {
        comicsNav(navController)
        charactersNav(navController)
        eventsNav(navController)
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
private fun NavGraphBuilder.comicsNav(navController: NavHostController) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.COMICS).route,
        route = Feature.COMICS.route
    ) {
        composable(NavCommand.ContentType(Feature.COMICS)) {
            ComicsScreen(onClick = {
                navController.navigate(NavCommand.ContentDetail(Feature.COMICS).createRoute(it.id))
            })
        }
        composable(NavCommand.ContentDetail(Feature.COMICS)) {
            ComicDetailScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                itemId = it.findArg(NavArg.ItemId)
            )
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
private fun NavGraphBuilder.charactersNav(navController: NavHostController) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.CHARACTERS).route,
        route = Feature.CHARACTERS.route
    ) {
        composable(NavCommand.ContentType(Feature.CHARACTERS)) {
            CharactersScreen(onClick = {
                navController.navigate(NavCommand.ContentDetail(Feature.CHARACTERS).createRoute(it.id))
            })
        }
        composable(NavCommand.ContentDetail(Feature.CHARACTERS)) {
            CharacterDetailScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                itemId = it.findArg(NavArg.ItemId)
            )
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
private fun NavGraphBuilder.eventsNav(navController: NavHostController) {
    navigation(
        startDestination = NavCommand.ContentType(Feature.EVENTS).route,
        route = Feature.EVENTS.route
    ) {
        composable(NavCommand.ContentType(Feature.EVENTS)) {
            EventsScreen(onClick = {
                navController.navigate(NavCommand.ContentDetail(Feature.EVENTS).createRoute(it.id))
            })
        }
        composable(NavCommand.ContentDetail(Feature.EVENTS)) {
            EventDetailScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                itemId = it.findArg(NavArg.ItemId)
            )
        }
    }
}

private fun NavGraphBuilder.composable(
    navCommand: NavCommand,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navCommand.route,
        arguments = navCommand.args
    ) {
        content(it)
    }
}

private inline fun <reified T> NavBackStackEntry.findArg(arg: NavArg): T {
    val value = arguments?.get(arg.key)
    requireNotNull(value)
    return value as T
}