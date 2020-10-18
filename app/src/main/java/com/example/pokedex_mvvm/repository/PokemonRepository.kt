package com.example.pokedex_mvvm.repository

import com.example.pokedex_mvvm.api.RetrofitInstance

class PokemonRepository {

    suspend fun getAllPokemons() =
        RetrofitInstance.api.getAllPokemons()

}