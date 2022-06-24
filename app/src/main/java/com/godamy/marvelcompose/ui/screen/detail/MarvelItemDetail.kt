package com.godamy.marvelcompose.ui.screen.detail

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.godamy.marvelcompose.R
import com.godamy.marvelcompose.data.entities.MarvelItem
import com.godamy.marvelcompose.data.entities.Reference
import com.godamy.marvelcompose.data.entities.ReferenceList

@ExperimentalMaterialApi
@Composable
fun MarvelItemDetail(
    loading: Boolean,
    marvelItem: MarvelItem?,
    onBackClick: () -> Unit
) {
    if (loading) {
        CircularProgressIndicator()
    }
    if (marvelItem != null) {
        MarvelItemDetailScaffold(
            marvelItem = marvelItem,
            onBackClick = onBackClick
        ) { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(padding)
            ) {
                item {
                    Header(marvelItem)
                }
                marvelItem.references.forEach {
                    val (icon, @StringRes stringRes) = it.type.createUiData()
                    section(icon, stringRes, it.items)
                }
            }
        }
    }
}

@ExperimentalMaterialApi
private fun LazyListScope.section(
    icon: ImageVector,
    @StringRes name: Int,
    items: List<Reference>
) {
    if (items.isNullOrEmpty()) return

    item {
        Text(
            text = stringResource(name),
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_16))
        )
    }
    items(items) {
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

private fun ReferenceList.Type.createUiData(): Pair<ImageVector, Int> = when (this) {
    ReferenceList.Type.CHARACTER -> Icons.Default.Person to R.string.characters
    ReferenceList.Type.COMIC -> Icons.Default.Book to R.string.comic
    ReferenceList.Type.STORY -> Icons.Default.Bookmark to R.string.stories
    ReferenceList.Type.EVENT -> Icons.Default.Event to R.string.events
    ReferenceList.Type.SERIES -> Icons.Default.Collections to R.string.series
}
