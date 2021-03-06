package com.godamy.marvelcompose.ui.navigation

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.godamy.marvelcompose.R
import com.godamy.marvelcompose.ui.screen.main.MarvelScreen

@Composable
fun DrawerContent(
    onOptionClick: (NavItem) -> Unit,
    selectedIndex: Int,
    drawerOptions: List<NavItem>
) {
    Box(
        modifier = Modifier
            .background(
                Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colors.primaryVariant,
                        MaterialTheme.colors.secondary
                    )
                )
            )
            .height(100.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterStart)
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
    drawerOptions.forEachIndexed { index, navItem ->
        val selected = selectedIndex == index
        val colors = MaterialTheme.colors

        val localContentColor = if (selected) colors.primary else colors.onBackground

        CompositionLocalProvider(
            LocalTextStyle provides MaterialTheme.typography.body1.copy(fontWeight = FontWeight.ExtraBold),
            LocalContentColor provides localContentColor
        ) {
            Row(modifier = Modifier
                .clickable { onOptionClick(navItem) }
                .fillMaxWidth()
                .padding(8.dp, 4.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(
                    color = if (selected) LocalContentColor.current.copy(alpha = 0.12f) else colors.surface
                )
                .padding(12.dp)
            ) {
                CompositionLocalProvider(
                    LocalContentAlpha provides ContentAlpha.medium
                ) {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.name,
                    )
                }
                Spacer(modifier = Modifier.width(24.dp))
                Text(text = navItem.name)
            }
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun NewPreview() {
    MarvelScreen {
        Column {
            DrawerContent(
                onOptionClick = {},
                selectedIndex = 0,
                drawerOptions = listOf(NavItem.HOME, NavItem.SETTINGS)
            )
        }
    }
}