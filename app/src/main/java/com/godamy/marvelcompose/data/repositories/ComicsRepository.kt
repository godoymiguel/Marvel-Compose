package com.godamy.marvelcompose.data.repositories

import com.godamy.marvelcompose.data.entities.*
import com.godamy.marvelcompose.data.network.entities.ApiComic
import com.godamy.marvelcompose.data.network.entities.asString
import com.godamy.marvelcompose.data.network.remote.ComicsService
import javax.inject.Inject

class ComicsRepository @Inject constructor(private val comicsService: ComicsService) {

    suspend fun get(format: Comic.Format? = null): Result<List<Comic>> =
        tryCall {
            comicsService
                .getComics(offset = 0, limit = 100, format = format?.toStringFormat())
                .data
                .results
                .map { it.toDomainModel() }
        }


    suspend fun find(comicId: Int): Result<Comic> =
        tryCall {
            comicsService
                .findComic(comicId)
                .data
                .results
                .first()
                .toDomainModel()
        }


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
