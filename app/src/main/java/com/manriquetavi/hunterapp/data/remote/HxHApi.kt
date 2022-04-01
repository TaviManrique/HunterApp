package com.manriquetavi.hunterapp.data.remote

import com.manriquetavi.hunterapp.domain.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HxHApi {

    @GET("/hunterxhunter/hunters")
    suspend fun getAllHunters(
        @Query("page") page: Int = 1
    ): ApiResponse

    @GET("/hunterxhunter/hunters/search")
    suspend fun searchHunters(
        @Query("name") name: String
    ): ApiResponse
}