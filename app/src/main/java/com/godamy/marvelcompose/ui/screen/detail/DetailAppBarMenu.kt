package com.godamy.marvelcompose.ui.screen.detail

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import com.godamy.marvelcompose.R
import com.godamy.marvelcompose.data.entities.Comic

@ExperimentalMaterialApi
@Composable
fun DetailAppBarMenu(comic: Comic) {
    var showMenu by remember { mutableStateOf(false) }
    val uriHandler = LocalUriHandler.current

    IconButton(onClick = { showMenu = !showMenu }) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = stringResource(id = R.string.more_action)
        )
        DropdownMenu(
            expanded = showMenu,
            onDismissRequest = { showMenu = !showMenu }
        ) {
            comic.urls.forEach {
                DropdownMenuItem(
                    onClick = {
                        uriHandler.openUri(it.url)
                        showMenu = !showMenu
                    }
                ) {
                    ListItem(text = { Text(text = it.type) })
                }
            }
        }
    }
}
