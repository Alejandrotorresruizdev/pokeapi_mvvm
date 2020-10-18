package com.example.pokedex_mvvm.models

import com.example.pokedex_mvvm.models.Pokemon.Pokemon

data class PokemonResponse (
    val pokemon: MutableList<Pokemon>,
    val count : Int
)