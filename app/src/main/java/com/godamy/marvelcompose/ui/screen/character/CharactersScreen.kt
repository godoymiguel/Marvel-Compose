package com.godamy.marvelcompose.ui.screen.character

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.godamy.marvelcompose.data.entities.Character
import com.godamy.marvelcompose.ui.screen.main.MarvelItemsVerticalGrid

@ExperimentalFoundationApi
@Composable
fun CharactersScreen(onClick: (Character) -> Unit, viewModel: CharacterViewModel = viewModel()) {
    MarvelItemsVerticalGrid(
        loading = viewModel.state.loading,
        items = viewModel.state.items,
        onClick = onClick
    )
}
