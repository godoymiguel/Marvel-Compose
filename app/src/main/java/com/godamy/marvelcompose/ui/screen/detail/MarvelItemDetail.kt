package com.godamy.marvelcompose.ui.screen.detail

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.godamy.marvelcompose.R
import com.godamy.marvelcompose.data.entities.MarvelItem
import com.godamy.marvelcompose.data.entities.Reference
import com.godamy.marvelcompose.data.entities.ReferenceList
import com.godamy.marvelcompose.data.entities.Result
import com.godamy.marvelcompose.ui.screen.common.ErrorMessage

@ExperimentalMaterialApi
@Composable
fun MarvelItemDetail(
    loading: Boolean,
    marvelItem: Result<MarvelItem?>,
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (loading) {
            CircularProgressIndicator()
        }
        marvelItem.fold({ ErrorMessage(error = it) }) {
            it?.let { item ->
                MarvelItemDetailScaffold(
                    marvelItem = item,
                    onBackClick = onBackClick
                ) { padding ->
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(padding)
                    ) {
                        item {
                            Header(item)
                        }
                        item.references.forEach { reference ->
                            val (icon, @StringRes stringRes) = reference.type.createUiData()
                            section(icon, stringRes, reference.items)
                        }
                    }
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
