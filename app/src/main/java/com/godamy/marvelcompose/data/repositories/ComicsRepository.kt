package com.godamy.marvelcompose.data.repositories

import com.godamy.marvelcompose.data.entities.Character
import com.godamy.marvelcompose.data.entities.Comic
import com.godamy.marvelcompose.data.entities.Url
import com.godamy.marvelcompose.data.network.ApiClient
import com.godamy.marvelcompose.data.network.entities.asString
import com.godamy.marvelcompose.data.network.entities.Comic as ServerComic

object ComicsRepository {

    suspend fun getComics(): List<Comic> {
        val result = ApiClient.comicsService.getComics(0, 100)

        return result.data.results.map {
            it.toDomainModel()
        }
    }

    suspend fun findComic(comicId: Int): Comic {
        val result = ApiClient.comicsService.findComic(comicId)
        return result.data.results.first().toDomainModel()
    }

    private fun ServerComic.toDomainModel(): Comic {
        val characters = characters.items.map {
            Character(it.name)
        }

        val urls = urls.map {
            Url(
                type = it.type,
                url = it.url
            )
        }
        return Comic(
            id,
            title,
            description ?: variantDescription,
            thumbnail.asString(),
            characters,
            urls
        )
    }
}
