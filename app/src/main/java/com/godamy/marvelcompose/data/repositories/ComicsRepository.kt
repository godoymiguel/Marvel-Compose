package com.godamy.marvelcompose.data.repositories

import com.godamy.marvelcompose.data.entities.Comic
import com.godamy.marvelcompose.data.network.ApiClient
import com.godamy.marvelcompose.data.network.entities.asString

object ComicsRepository {
    
    suspend fun getComics(): List<Comic> {
        val result = ApiClient.comicsService.getComics(0, 100)
        
        return result.data.results.map { 
            Comic(
                it.id,
                it.title,
                it.description ?: it.variantDescription,
                it.thumbnail.asString()
            )
        }
    }
}
