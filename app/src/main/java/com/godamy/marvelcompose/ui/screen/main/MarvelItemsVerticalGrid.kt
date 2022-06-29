package com.godamy.marvelcompose.ui.screen.main

import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.dimensionResource
import com.godamy.marvelcompose.R
import com.godamy.marvelcompose.data.entities.MarvelItem
import com.godamy.marvelcompose.data.entities.Result
import com.godamy.marvelcompose.ui.screen.common.ErrorMessage
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun <T : MarvelItem> MarvelItemsVerticalGrid(
    loading: Boolean,
    marvelItems: Result<List<T>>,
    onClick: (T) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (loading) {
            CircularProgressIndicator()
        }
        marvelItems.fold({ ErrorMessage(error = it) }) {
            if (it.isNotEmpty()) {
                val sheetState =
                    rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
                val coroutineScope = rememberCoroutineScope()

                var bottomSheetItem by remember {
                    mutableStateOf<T?>(null)
                }

                val lifecycleOwner = LocalLifecycleOwner.current
                val backDispatcher =
                    requireNotNull(LocalOnBackPressedDispatcherOwner.current).onBackPressedDispatcher

                val backCallback = remember {
                    object : OnBackPressedCallback(true) {
                        override fun handleOnBackPressed() {
                            coroutineScope.launch { sheetState.hide() }
                        }
                    }
                }

                DisposableEffect(lifecycleOwner, backDispatcher) {
                    backDispatcher.addCallback(lifecycleOwner, backCallback)

                    onDispose { backCallback.remove() }
                }

                ModalBottomSheetLayout(
                    sheetContent = {
                        MarvelItemBottomSheet(
                            item = bottomSheetItem,
                            onGoDetail = {
                                coroutineScope.launch {
                                    sheetState.hide()
                                    onClick(it)
                                }
                            }
                        )
                    },
                    sheetState = sheetState
                ) {
                    LazyVerticalGrid(
                        cells = GridCells.Adaptive(dimensionResource(id = R.dimen.grid_cell_180)),
                        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_4))
                    ) {
                        items(it) { item ->
                            MarvelItem(
                                marvelItem = item,
                                modifier = Modifier.clickable { onClick(item) },
                                onItemMoreClick = {
                                    bottomSheetItem = item
                                    coroutineScope.launch {
                                        sheetState.show()
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
