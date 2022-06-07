package com.godamy.marvelcompose.data.network

import com.godamy.marvelcompose.data.network.entities.ApiResponse
import com.godamy.marvelcompose.data.network.entities.Comic
import retrofit2.http.GET
import retrofit2.http.Query

interface ComicsService {

    @GET("/v1/public/comics")
    suspend fun getComics(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): ApiResponse<Comic>
}
