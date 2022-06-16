package com.godamy.marvelcompose.ui.screen.character

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import com.godamy.marvelcompose.data.entities.Comic
import com.godamy.marvelcompose.data.repositories.ComicsRepository
import com.godamy.marvelcompose.ui.screen.main.MarvelItemsList

@ExperimentalFoundationApi
@Composable
fun CharactersScreen(onClick: (Comic) -> Unit) {
    var charactersState by rememberSaveable {
        mutableStateOf(emptyList<Comic>())
    }

    LaunchedEffect(Unit) {
        charactersState = ComicsRepository.get()
    }

    MarvelItemsList(charactersState, onClick)
}
