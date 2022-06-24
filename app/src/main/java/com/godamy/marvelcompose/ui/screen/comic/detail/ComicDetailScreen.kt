package com.godamy.marvelcompose.ui.screen.comic.detail

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.godamy.marvelcompose.ui.screen.detail.MarvelItemDetail

@ExperimentalMaterialApi
@Composable
fun ComicDetailScreen(onBackClick: () -> Unit, viewModel: ComicDetailViewModel = viewModel()) {
    MarvelItemDetail(
        loading = viewModel.state.loading,
        marvelItem = viewModel.state.comic,
        onBackClick = onBackClick
    )
}
