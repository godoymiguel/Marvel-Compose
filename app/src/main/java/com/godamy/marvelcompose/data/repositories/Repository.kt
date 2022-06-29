package com.godamy.marvelcompose.data.repositories

import com.godamy.marvelcompose.data.entities.MarvelItem
import com.godamy.marvelcompose.data.entities.Result
import com.godamy.marvelcompose.data.entities.tryCall

abstract class Repository<T : MarvelItem> {

    private var cache: List<T> = emptyList()

    internal suspend fun get(getAction: suspend () -> List<T>): Result<List<T>> =
        tryCall {
            cache.ifEmpty {
                getAction()
            }
        }

    internal suspend fun find(
        id: Int,
        findActionRemote: suspend () -> T
    ): Result<T> =
        tryCall {
            val item = cache.find { it.id == id }

            item ?: findActionRemote()
        }
}
