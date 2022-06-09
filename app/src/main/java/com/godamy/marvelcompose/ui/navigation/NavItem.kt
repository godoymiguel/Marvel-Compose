package com.godamy.marvelcompose.ui.navigation

import androidx.navigation.navArgument

sealed class NavItem(
    internal val baseRoute: String,
    private val navArgs: List<NavArg> = emptyList()
) {
    object Comics: NavItem("comics")
    object ComicDetail: NavItem("comicDetail", listOf(NavArg.ItemId)) {
        fun createRoute(itemId: Int) = "$baseRoute/$itemId"
    }

    val route = run {
        val argValues = navArgs.map { "{${it.key}}" }
        listOf(baseRoute)
            .plus(argValues)
            .joinToString("/")
    }

    val args = navArgs.map {
        navArgument(it.key) {type = it.navType}
    }
}