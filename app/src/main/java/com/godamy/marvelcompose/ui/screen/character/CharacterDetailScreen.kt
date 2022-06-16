package com.godamy.marvelcompose.ui.screen.character

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import com.godamy.marvelcompose.data.entities.Character
import com.godamy.marvelcompose.data.entities.Comic
import com.godamy.marvelcompose.data.repositories.CharactersRepository
import com.godamy.marvelcompose.data.repositories.ComicsRepository
import com.godamy.marvelcompose.ui.screen.detail.MarvelItemDetail

@ExperimentalMaterialApi
@Composable
fun CharacterDetailScreen(onBackClick: () -> Unit, itemId: Int) {
    var characterState by remember {
        mutableStateOf<Character?>(null)
    }
    LaunchedEffect(Unit) {
        characterState = CharactersRepository.find(itemId)

    }
    characterState?.let { MarvelItemDetail(it, onBackClick) }
}
