package com.godamy.marvelcompose.ui.screen.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.progressSemantics
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@ExperimentalMaterialApi
@Composable
fun SettingScreen() {
    val state = rememberScrollState()
    BottomSheet {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.scrollable(state = state, orientation = Orientation.Vertical)
            ) {
                Tabs()
                IconToggleButton()
                BadgedBox()
                Checkbox()
                RadioButton()
                Switch()
                Sliders()
                LinearProgressIndicator(progress = 0.5f)
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun BottomSheet(content: @Composable (PaddingValues) -> Unit) {
    val state = rememberBottomSheetScaffoldState()
    BottomSheetScaffold(
        scaffoldState = state,
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.LightGray),
            ) {
                Column {
                    Text(
                        text = "Settings",
                        style = MaterialTheme.typography.h3,
                        modifier = Modifier.fillMaxWidth()
                    )
                    ExposedDropdown()
                }
            }
        },
        content = content
    )
}

@ExperimentalMaterialApi
@Composable
private fun ExposedDropdown() {
    val options = listOf("A", "B", "C", "D")
    var selected by remember { mutableStateOf(0) }
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {
        TextField(
            value = options[selected],
            onValueChange = {},
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            }
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = !expanded }
        ) {
            options.forEachIndexed { index, text ->
                DropdownMenuItem(onClick = {
                    selected = index
                    expanded = !expanded
                }) {
                    Text(text = text)
                }
            }
        }
    }
}

@Composable
private fun Tabs() {
    TabRow(selectedTabIndex = 0) {
        LeadingIconTab(
            selected = true,
            onClick = { /*TODO*/ },
            text = { Text(text = "Settings") },
            icon = { Icon(imageVector = Icons.Default.Settings, contentDescription = null) }
        )
        Tab(
            selected = false,
            onClick = { /*TODO*/ },
            text = { Text(text = "Settings") }
        )
    }
}

@ExperimentalMaterialApi
@Composable
private fun Sliders() {
    var value by remember { mutableStateOf(50f) }
    var valueRange by remember { mutableStateOf(30f..60f) }
    Column {
        Slider(
            value = value,
            onValueChange = { value = it },
            valueRange = 0f..100f,
            steps = 10
        )
        RangeSlider(
            values = valueRange,
            onValueChange = { valueRange = it },
            valueRange = 0f..100f,
            steps = 10,
            colors = SliderDefaults.colors(
                thumbColor = Color.Blue,
                activeTickColor = Color.Green
            )
        )
    }
}

@Composable
private fun Switch() {
    var state by remember { mutableStateOf(false) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { state = !state }
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
    IconToggleButton(
        checked = favorite,
        onCheckedChange = { favorite = it },
        modifier = Modifier.progressSemantics(0.5f)
    ) {
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