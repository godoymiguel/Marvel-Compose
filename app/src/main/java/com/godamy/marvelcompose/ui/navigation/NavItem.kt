package com.godamy.marvelcompose.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Face
import androidx.compose.ui.graphics.vector.ImageVector
import com.godamy.marvelcompose.R

enum class NavItem(
    val navCommand: NavCommand,
    val icon: ImageVector,
    @StringRes val title: Int
) {
    COMICS(NavCommand.ContentType(Feature.COMICS), Icons.Default.Book, R.string.comic),
    CHARACTERS(NavCommand.ContentType(Feature.CHARACTERS), Icons.Default.Face, R.string.characters),
    EVENTS(NavCommand.ContentType(Feature.EVENTS), Icons.Default.Event, R.string.events)
}