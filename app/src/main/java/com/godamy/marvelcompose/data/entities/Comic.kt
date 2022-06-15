package com.godamy.marvelcompose.data.entities

data class Comic(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: String,
    val character: List<Character>,
    val urls: List<Url>
)
