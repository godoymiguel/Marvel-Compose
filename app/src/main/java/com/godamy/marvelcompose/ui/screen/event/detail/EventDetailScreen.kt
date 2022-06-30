package com.godamy.marvelcompose.ui.screen.event.detail

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.godamy.marvelcompose.ui.screen.detail.MarvelItemDetail

@ExperimentalMaterialApi
@Composable
fun EventDetailScreen(onBackClick: () -> Unit, viewModel: EventDetailViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    MarvelItemDetail(
        loading = state.loading,
        marvelItem = state.event,
        onBackClick = onBackClick
    )
}
