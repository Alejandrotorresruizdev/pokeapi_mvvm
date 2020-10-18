package com.example.pokedex_mvvm.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokedex_mvvm.repository.PokemonRepository
import com.example.pokedex_mvvm.ui.view_models.PokemonListViewModel

class PokemonViewModellProviderFactory(val pokemonRepository: PokemonRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PokemonListViewModel(pokemonRepository) as T
    }
}
