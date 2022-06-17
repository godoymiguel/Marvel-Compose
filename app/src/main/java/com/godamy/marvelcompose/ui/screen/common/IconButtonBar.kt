package com.godamy.marvelcompose.ui.screen.common

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun IconButtonBar(
    onClick: () -> Unit,
    menu: ImageVector,
    description: String? = null
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = menu,
            contentDescription = description
        )
    }
}
