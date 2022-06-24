package com.godamy.marvelcompose.ui.screen.event

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.godamy.marvelcompose.data.entities.Event
import com.godamy.marvelcompose.data.repositories.EventsRepository
import com.godamy.marvelcompose.ui.screen.main.MarvelItemsVerticalGrid

@ExperimentalFoundationApi
@Composable
fun EventsScreen(onClick: (Event) -> Unit, viewModel: EventViewModel = viewModel()) {
    MarvelItemsVerticalGrid(
        loading = viewModel.state.loading,
        items = viewModel.state.items,
        onClick = onClick
    )
}
