package com.godamy.marvelcompose.ui.navigation

import androidx.navigation.NavType

enum class NavArg(val key: String, val navType: NavType<*>) {
    ItemId("itemId", NavType.IntType)
}
