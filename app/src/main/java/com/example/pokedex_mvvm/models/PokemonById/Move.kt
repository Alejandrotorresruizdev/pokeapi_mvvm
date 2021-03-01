package com.example.pokedex_mvvm.models.PokemonById

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)