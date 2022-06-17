package com.godamy.marvelcompose.ui.screen.character

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import com.godamy.marvelcompose.data.entities.Character
import com.godamy.marvelcompose.data.repositories.CharactersRepository
import com.godamy.marvelcompose.ui.screen.main.MarvelItemsVerticalGrid

@ExperimentalFoundationApi
@Composable
fun CharactersScreen(onClick: (Character) -> Unit) {
    var charactersState by rememberSaveable {
        mutableStateOf(emptyList<Character>())
    }

    LaunchedEffect(Unit) {
        charactersState = CharactersRepository.get()
    }

    MarvelItemsVerticalGrid(charactersState, onClick)
}
