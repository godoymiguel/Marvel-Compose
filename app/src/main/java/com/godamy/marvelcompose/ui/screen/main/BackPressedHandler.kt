package com.godamy.marvelcompose.ui.screen.main

import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner

@Composable
fun BackPressedHandler(enabled: Boolean, onBack: () -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val backDispatcher =
        requireNotNull(LocalOnBackPressedDispatcherOwner.current).onBackPressedDispatcher

    val backCallback = remember {
        object : OnBackPressedCallback(enabled) {
            override fun handleOnBackPressed() {
                onBack()
            }
        }
    }

    SideEffect {
        backCallback.isEnabled = enabled
    }

    DisposableEffect(lifecycleOwner, backDispatcher) {
        backDispatcher.addCallback(lifecycleOwner, backCallback)

        onDispose { backCallback.remove() }
    }
}
