package com.example.pokedex_mvvm.models.Pokemon

import java.io.Serializable

data class Result(
    val name: String,
    val url: String
) : Serializable