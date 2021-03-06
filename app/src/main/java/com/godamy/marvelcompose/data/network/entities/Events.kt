package com.godamy.marvelcompose.data.network.entities

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<ApiEvent>,
    val returned: Int
)