package com.example.pokedex_mvvm.api

import com.example.pokedex_mvvm.models.Pokemon.PokemonResponse
import com.example.pokedex_mvvm.models.PokemonById.PokemonByIdResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap




interface PokemonAPi {

    @GET("pokemon")
    suspend fun getAllPokemons(
        @Query("limit")
        limit: Int = 10,
        @Query("offset")
        offset: Int = 0
    ): Response<PokemonResponse>

    @GET("https://pokeapi.co/api/v2/pokemon/{id}")
    suspend fun getPokemonById(
        @Path("id") id: Int
    ): Response<PokemonByIdResult>

}