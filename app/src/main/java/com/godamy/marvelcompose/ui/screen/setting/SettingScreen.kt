package com.godamy.marvelcompose.ui.screen.setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun SettingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Settings",
                style = MaterialTheme.typography.h3
            )
            IconToggleButton()
            BadgedBox()
        }
    }
}

@Composable
private fun BadgedBox() {
    var badge by remember { mutableStateOf(0) }
    IconButton(onClick = { badge++ }) {
        BadgedBox(badge = {
            Badge {
                Text(text = badge.toString())
            }
        }) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notifications"
            )
        }
    }
}

@Composable
private fun IconToggleButton() {
    var favorite by remember { mutableStateOf(false) }
    IconToggleButton(checked = favorite, onCheckedChange = { favorite = it }) {
        Icon(
            imageVector = if (favorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = "Favorite"
        )
    }
}