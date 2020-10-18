package com.example.pokedex_mvvm.models.Pokemon

data class PokemonResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)