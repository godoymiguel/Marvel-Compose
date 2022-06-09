package com.godamy.marvelcompose.ui.screens.comics.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.godamy.marvelcompose.data.entities.Character
import com.godamy.marvelcompose.data.entities.Comic
import com.godamy.marvelcompose.data.repositories.ComicsRepository

@ExperimentalMaterialApi
@Composable
fun ComicDetailScreen(comicId: Int) {
    var comicState by remember {
        mutableStateOf<Comic?>(null)
    }
    LaunchedEffect(Unit) {
        comicState = ComicsRepository.findComic(comicId)

    }
    comicState?.let {
        ComicDetailScreen(comic = it)
    }
}

@ExperimentalMaterialApi
@Composable
fun ComicDetailScreen(comic: Comic) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        item {
            Header(comic)
        }

        section(Icons.Default.AccountCircle, "Characters", comic.character)
    }
}

@ExperimentalMaterialApi
private fun LazyListScope.section(
    icon: ImageVector,
    title: String,
    character: List<Character>
) {
    item {
        Text(
            text = title,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(16.dp)
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

@Composable
fun Header(comic: Comic) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = rememberAsyncImagePainter(comic.thumbnail),
            contentDescription = comic.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .aspectRatio(1f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = comic.title,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = comic.description,
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}