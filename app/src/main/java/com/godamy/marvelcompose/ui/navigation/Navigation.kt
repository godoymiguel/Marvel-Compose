package com.godamy.marvelcompose.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.godamy.marvelcompose.ui.screen.character.detail.CharacterDetailScreen
import com.godamy.marvelcompose.ui.screen.character.CharactersScreen
import com.godamy.marvelcompose.ui.screen.comic.detail.ComicDetailScreen
import com.godamy.marvelcompose.ui.screen.comic.ComicsScreen
import com.godamy.marvelcompose.ui.screen.event.detail.EventDetailScreen
import com.godamy.marvelcompose.ui.screen.event.EventsScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Feature.COMICS.route
    ) {
        comicsNav(navController)
        charactersNav(navController)
        eventsNav(navController)
        settingsNav()
    }
}

private fun NavGraphBuilder.settingsNav() {
    composable(NavCommand.ContentType(Feature.SETTINGS)) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Settings",
                style = MaterialTheme.typography.h3
            )
        }
    }
}

@ExperimentalPagerApi
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
                }
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
                navController.navigate(
                    NavCommand.ContentDetail(Feature.CHARACTERS).createRoute(it.id)
                )
            })
        }
        composable(NavCommand.ContentDetail(Feature.CHARACTERS)) {
            CharacterDetailScreen(
                onBackClick = {
                    navController.popBackStack()
                }
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
                }
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