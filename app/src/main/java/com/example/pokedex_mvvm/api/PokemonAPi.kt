package com.example.pokedex_mvvm.api

import com.example.pokedex_mvvm.models.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonAPi {

    @GET("/pokemon?limit=251&offset=0")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        pageNumber: Int = 1
    ): Response<PokemonResponse>
}