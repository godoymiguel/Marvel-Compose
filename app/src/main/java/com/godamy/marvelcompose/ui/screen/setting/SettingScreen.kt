package com.godamy.marvelcompose.ui.screen.setting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp

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
            Checkbox()
            RadioButton()
            Switch()
        }
    }
}

@Composable
private fun Switch() {
    var state by remember { mutableStateOf(false) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier =
        Modifier
            .fillMaxSize()
            .padding(16.dp)
            .clickable { state = !state }
    ) {
        Text(
            text = "Switch Action",
            modifier = Modifier.weight(1f)
        )
        Switch(
            checked = state,
            onCheckedChange = { state = it }
        )
    }
}

@Composable
private fun RadioButton() {
    val radioOptions = listOf("RB1", "RB2", "RB3", "RB4")
    var selected by remember { mutableStateOf(-1) }
    Column {
        radioOptions.forEachIndexed { index, text ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(selected = selected == index, onClick = { selected = index })
                Text(text = text)
            }
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

@Composable
private fun Checkbox() {
    var state1 by remember { mutableStateOf(false) }
    var state2 by remember { mutableStateOf(true) }
    val triState = when {
        state1 && state2 -> ToggleableState.On
        !state1 && !state2 -> ToggleableState.Off
        else -> ToggleableState.Indeterminate
    }
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            TriStateCheckbox(state = triState, onClick = {
                if (triState == ToggleableState.On) {
                    state1 = false
                    state2 = false
                } else {
                    state1 = true
                    state2 = true
                }
            })
            Text(text = "All")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = state1, onCheckedChange = { state1 = it })
            Text(text = "Checkbox 1")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = state2, onCheckedChange = { state2 = it })
            Text(text = "Checkbox 2")
        }
    }
}