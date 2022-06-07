package com.godamy.marvelcompose.data.network.entities

data class Thumbnail(
    val extension: String,
    val path: String
)

fun Thumbnail.asString(): String = "$path.$extension".replace("http","https")
