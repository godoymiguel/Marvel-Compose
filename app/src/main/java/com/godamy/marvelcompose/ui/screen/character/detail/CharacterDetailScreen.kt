package com.godamy.marvelcompose.ui.screen.character.detail

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.godamy.marvelcompose.data.entities.Character
import com.godamy.marvelcompose.data.repositories.CharactersRepository
import com.godamy.marvelcompose.ui.screen.detail.MarvelItemDetail

@ExperimentalMaterialApi
@Composable
fun CharacterDetailScreen(
    onBackClick: () -> Unit,
    viewModel: CharacterDetailViewModel = viewModel()
) {
    MarvelItemDetail(
        loading = viewModel.state.loading,
        marvelItem = viewModel.state.character,
        onBackClick = onBackClick
    )
}
