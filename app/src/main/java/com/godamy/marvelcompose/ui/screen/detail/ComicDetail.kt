package com.godamy.marvelcompose.ui.screen.detail

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.godamy.marvelcompose.R
import com.godamy.marvelcompose.data.entities.Character
import com.godamy.marvelcompose.data.entities.Comic

@ExperimentalMaterialApi
@Composable
fun ComicDetail(comic: Comic, onBackClick: () -> Unit) {
    ComicDetailScaffold(
        comic = comic,
        onBackClick = onBackClick
    ) { padding ->
        val titleCharacter = stringResource(id = R.string.characters)
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        ) {
            item {
                Header(comic)
            }
            section(Icons.Default.AccountCircle, titleCharacter, comic.character)
        }
    }
}

@ExperimentalMaterialApi
private fun LazyListScope.section(
    icon: ImageVector,
    title: String,
    character: List<Character>
) {
    if (character.isNullOrEmpty()) return

    item {
        Text(
            text = title,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_16))
        )
    }
    items(character) {
        ListItem(
            icon = {
                Icon(
                    imageVector = icon,
                    contentDescription = it.name
                )
            },
            text = { Text(text = it.name) }
        )
    }
}