package com.godamy.marvelcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.godamy.marvelcompose.data.entities.Character
import com.godamy.marvelcompose.ui.screens.characters.CharacterScreen
import com.godamy.marvelcompose.ui.screens.characters.CharactersScreenPreview
import com.godamy.marvelcompose.ui.theme.MarvelComposeTheme

@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val character = (1..10).map {
                Character(
                    it,
                    "Name $it",
                    "Description $it",
                    "https://via.placeholder.com/150x225/FFFF00/000000?text=name$it"
                )
            }
            MarvelApp {
                CharacterScreen(characters = character)
            }
        }
    }
}

@Composable
fun MarvelApp(content: @Composable () -> Unit) {
    MarvelComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}