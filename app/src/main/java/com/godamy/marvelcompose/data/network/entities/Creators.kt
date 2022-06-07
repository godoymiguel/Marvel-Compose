package com.godamy.marvelcompose.data.network.entities

data class Creators(
    val available: Int,
    val collectionURI: String,
    val items: List<Creator>,
    val returned: Int
)