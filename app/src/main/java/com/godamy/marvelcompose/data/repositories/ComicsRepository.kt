package com.godamy.marvelcompose.data.repositories

import com.godamy.marvelcompose.data.entities.Comic
import com.godamy.marvelcompose.data.entities.ReferenceList
import com.godamy.marvelcompose.data.entities.Url
import com.godamy.marvelcompose.data.network.ApiClient
import com.godamy.marvelcompose.data.network.entities.ApiComic
import com.godamy.marvelcompose.data.network.entities.asString

object ComicsRepository {

    suspend fun get(format: Comic.Format): List<Comic> =
        ApiClient
            .comicsService
            .getComics(offset = 0, limit = 20, format = format.toStringFormat())
            .data
            .results
            .map { it.toDomainModel() }


    suspend fun find(comicId: Int): Comic =
        ApiClient
            .comicsService
            .findComic(comicId)
            .data
            .results
            .first()
            .toDomainModel()


    private fun ApiComic.toDomainModel(): Comic =
        Comic(
            id,
            title,
            description ?: variantDescription,
            thumbnail.asString(),
            format.toDomain(),
            listOf(
                characters.toDomainModel(ReferenceList.Type.CHARACTER),
                series.toDomainModel(ReferenceList.Type.SERIES)
            ),
            urls.map { Url(type = it.type, url = it.url) }
        )


    private fun String.toDomain(): Comic.Format = when (this) {
        "magazine" -> Comic.Format.MAGAZINE
        "trade paperback" -> Comic.Format.TRADE_PAPERBACK
        "hardcover" -> Comic.Format.HARDCOVER
        "digest" -> Comic.Format.DIGEST
        "graphic novel" -> Comic.Format.GRAPHIC_NOVEL
        "infinite comic" -> Comic.Format.INFINITE_COMIC
        "digital comic" -> Comic.Format.DIGITAL_COMIC
        else -> Comic.Format.COMIC
    }

    private fun Comic.Format.toStringFormat(): String = when (this) {
        Comic.Format.COMIC -> "comic"
        Comic.Format.MAGAZINE -> "magazine"
        Comic.Format.TRADE_PAPERBACK -> "trade paperback"
        Comic.Format.HARDCOVER -> "hardcover"
        Comic.Format.DIGEST -> "digest"
        Comic.Format.GRAPHIC_NOVEL -> "graphic novel"
        Comic.Format.DIGITAL_COMIC -> "digital comic"
        Comic.Format.INFINITE_COMIC -> "infinite comic"
    }
}
