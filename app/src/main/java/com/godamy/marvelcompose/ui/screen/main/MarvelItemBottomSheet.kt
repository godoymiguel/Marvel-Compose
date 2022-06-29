package com.godamy.marvelcompose.ui.screen.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.godamy.marvelcompose.R
import com.godamy.marvelcompose.data.entities.MarvelItem

@Composable
fun <T : MarvelItem> MarvelItemBottomSheet(
    item: T?,
    onGoDetail: (T) -> Unit
) {
    if (item != null) {
        Row(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_8)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.space_by_8))
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = item.thumbnail),
                contentDescription = item.title,
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen.image_width_96))
                    .aspectRatio(1 / 1.5f)
                    .background(Color.LightGray)
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.space_by_8))
            ) {
                Text(text = item.title, style = MaterialTheme.typography.h6)
                Text(text = item.description)
                Button(
                    onClick = { onGoDetail(item) },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(text = stringResource(id = R.string.go_to_detail))
                }
            }
        }
    } else {
        Spacer(modifier = Modifier.height(1.dp))
    }
}