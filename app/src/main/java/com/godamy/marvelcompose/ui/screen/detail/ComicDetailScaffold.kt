package com.godamy.marvelcompose.ui.screen.detail

import android.content.Context
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.app.ShareCompat
import com.godamy.marvelcompose.R
import com.godamy.marvelcompose.data.entities.Comic

@ExperimentalMaterialApi
@Composable
fun ComicDetailScaffold(
    comic: Comic,
    onBackClick: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            DetailAppBar(comic, onBackClick)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { shareComic(context, comic) }) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = stringResource(id = R.string.share_comic)
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
