package com.godamy.marvelcompose.ui.screen.character

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.godamy.marvelcompose.data.entities.Character
import com.godamy.marvelcompose.ui.screen.common.ErrorMessage
import com.godamy.marvelcompose.ui.screen.main.MarvelItemsVerticalGrid

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun CharactersScreen(onClick: (Character) -> Unit, viewModel: CharacterViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()
    MarvelItemsVerticalGrid(
        loading = state.loading,
        marvelItems = state.items,
        onClick = onClick
    )
}
