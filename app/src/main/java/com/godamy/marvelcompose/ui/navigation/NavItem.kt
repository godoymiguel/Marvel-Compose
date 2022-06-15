package com.godamy.marvelcompose.ui.navigation

import androidx.navigation.navArgument

sealed class NavItem(
    internal val feature: Feature,
    internal val subRoute: String = "home",
    private val navArgs: List<NavArg> = emptyList()
) {
    class ContentType(feature: Feature) : NavItem(feature)

    class ContentDetail(feature: Feature) : NavItem(
        feature,
        "detail",
        listOf(NavArg.ItemId)
    ) {
        fun createRoute(itemId: Int) = "${feature.route}/$subRoute/$itemId"
    }

    val route = run {
        val argValues = navArgs.map { "{${it.key}}" }
        listOf(feature.route, subRoute)
            .plus(argValues)
            .joinToString("/")
    }

    val args = navArgs.map {
        navArgument(it.key) {type = it.navType}
    }
}