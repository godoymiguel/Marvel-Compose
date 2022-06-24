package com.godamy.marvelcompose.ui.screen.event.detail

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.godamy.marvelcompose.ui.screen.detail.MarvelItemDetail

@ExperimentalMaterialApi
@Composable
fun EventDetailScreen(onBackClick: () -> Unit, viewModel: EventDetailViewModel = viewModel()) {
    MarvelItemDetail(
        loading = viewModel.state.loading,
        marvelItem = viewModel.state.event,
        onBackClick = onBackClick
    )
}
