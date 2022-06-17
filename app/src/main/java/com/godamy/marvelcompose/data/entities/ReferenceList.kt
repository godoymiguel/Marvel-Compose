package com.godamy.marvelcompose.data.entities

data class ReferenceList (
    val type: Type,
    val items: List<Reference>
) {
    enum class Type {
        CHARACTER,
        COMIC,
        STORY,
        EVENT,
        SERIES
    }
}