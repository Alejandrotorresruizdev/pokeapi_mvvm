package com.example.pokedex_mvvm.repository

import com.example.pokedex_mvvm.api.RetrofitInstance

class PokemonRepository {

    suspend fun getAllPokemons(limit: Int,offset:Int) =
        RetrofitInstance.api.getAllPokemons(limit,offset)

    suspend fun getPokemonById(id : Int) =
        RetrofitInstance.api.getPokemonById(id)
}