package com.godamy.marvelcompose.data.network.remote

import com.godamy.marvelcompose.data.network.entities.ApiResponse
import com.godamy.marvelcompose.data.network.entities.ApiComic
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ComicsService {

    @GET("/v1/public/comics")
    suspend fun getComics(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("format") format: String?
    ): ApiResponse<ApiComic>

    @GET("/v1/public/comics/{comicId}")
    suspend fun findComic(
        @Path("comicId") comicId: Int
    ): ApiResponse<ApiComic>
}
