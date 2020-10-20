package com.example.pokedex_mvvm.api

import com.example.pokedex_mvvm.models.Pokemon.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonAPi {

    @GET("pokemon")
    suspend fun getAllPokemons(
        @Query("limit")
        limit: Int = 10,
        @Query("offset")
        offset: Int = 0
    ): Response<PokemonResponse>
}