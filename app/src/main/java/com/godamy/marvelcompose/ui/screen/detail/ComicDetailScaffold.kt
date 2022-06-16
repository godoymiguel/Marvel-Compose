package com.godamy.marvelcompose.ui.screen.detail

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.app.ShareCompat
import com.godamy.marvelcompose.R
import com.godamy.marvelcompose.data.entities.Comic
import com.godamy.marvelcompose.data.entities.MarvelItem
import com.godamy.marvelcompose.ui.screen.common.IconButtonBar

@ExperimentalMaterialApi
@Composable
fun ComicDetailScaffold(
    comic: MarvelItem,
    onBackClick: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            DetailAppBar(comic, onBackClick)
        },
        floatingActionButton = {
            if (comic.urls.isNotEmpty()) {
                FloatingActionButton(onClick = { shareComic(context, comic) }) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = stringResource(id = R.string.share_comic)
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        bottomBar = {
            BottomAppBar(cutoutShape = CircleShape) {
                IconButtonBar(
                    onClick = {},
                    menu = Icons.Default.Menu,
                    description = stringResource(id = R.string.menu)
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButtonBar(
                    onClick = {},
                    menu = Icons.Default.Favorite,
                    description = stringResource(id = R.string.favorite)
                )
            }
        },
        content = content
    )
}

private fun shareComic(context: Context, comic: Comic) {
    ShareCompat
        .IntentBuilder(context)
        .setType("text/plain")
        .setSubject(comic.title)
        .setText(comic.urls.first().url)
        .intent
        .also(context::startActivity)
}
