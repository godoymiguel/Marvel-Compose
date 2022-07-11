package com.godamy.marvelcompose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import arrow.core.Either
import com.godamy.marvelcompose.data.entities.Character
import com.godamy.marvelcompose.ui.screen.main.MarvelItemsVerticalGrid
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalMaterialApi
@ExperimentalFoundationApi
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    private val items =
        (1..100).map {
            Character(
                id = it,
                title = "Title $it",
                description = "Description $it",
                thumbnail = "",
                references = emptyList(),
                urls = emptyList()
            )
        }

    @Before
    fun setUp() {
        composeTestRule.setContent {
            MarvelItemsVerticalGrid(
                loading = false,
                marvelItems = Either.Right(items),
                onClick = {}
            )
        }
    }

    @Test
    fun navigatesTo51(): Unit = with(composeTestRule) {
        onNode(hasScrollToIndexAction()).performScrollToIndex(25)
        onNodeWithText("Title 51").assertExists()
    }

    @Test
    fun navigatesTo51AndShowBottomSheet(): Unit = with(composeTestRule) {
        onNode(hasScrollToIndexAction()).performScrollToIndex(25)
        onNode(
            hasParent(hasText("Title 51")) and
                    hasContentDescription(appContext.getString(R.string.more_action))
        ).performClick()
        onNode(
            hasAnySibling(hasText(appContext.getString(R.string.go_to_detail)))
                    and hasText("Title 51")
        ).assertExists()
    }
}