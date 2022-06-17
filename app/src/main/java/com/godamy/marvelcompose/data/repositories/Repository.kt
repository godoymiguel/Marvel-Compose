package com.godamy.marvelcompose.data.repositories

import com.godamy.marvelcompose.data.entities.MarvelItem

abstract class Repository<T : MarvelItem> {

    private var cache: List<T> = emptyList()

    internal suspend fun get(getAction: suspend () -> List<T>): List<T> {
        if (cache.isEmpty()) {
            cache = getAction()
        }

        return cache
    }

    internal suspend fun find(
        id: Int,
        findActionRemote: suspend () -> T
    ): T {
        val item = cache.find { it.id == id }

        if (item != null) return item

        return findActionRemote()
    }
}
