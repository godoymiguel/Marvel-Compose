package com.godamy.marvelcompose.ui.screen.detail

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.stringResource
import com.godamy.marvelcompose.R
import com.godamy.marvelcompose.data.entities.Comic
import com.godamy.marvelcompose.ui.screen.common.ArrowBack

@ExperimentalMaterialApi
@Composable
fun DetailAppBar(
    comic: Comic,
    onBackClick: () -> Unit
) {
    TopAppBar(
        title = { Text(text = comic.title) },
        navigationIcon = {
            ArrowBack(onBackClick)
        },
        actions = {
            DetailAppBarMenu(comic)
        }
    )
}
