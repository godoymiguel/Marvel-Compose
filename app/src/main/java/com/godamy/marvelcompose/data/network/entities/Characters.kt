package com.godamy.marvelcompose.data.network.entities

data class Characters(
    val available: Int,
    val collectionURI: String,
    val items: List<ApiCharacter>,
    val returned: Int
)