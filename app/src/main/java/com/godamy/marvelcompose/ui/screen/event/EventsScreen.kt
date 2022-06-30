package com.godamy.marvelcompose.ui.screen.event

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.godamy.marvelcompose.data.entities.Event
import com.godamy.marvelcompose.ui.screen.main.MarvelItemsVerticalGrid

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun EventsScreen(onClick: (Event) -> Unit, viewModel: EventViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsState()
    MarvelItemsVerticalGrid(
        loading = state.loading,
        marvelItems = state.items,
        onClick = onClick
    )
}
