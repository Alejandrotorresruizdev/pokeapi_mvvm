package com.example.pokedex_mvvm.ui.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex_mvvm.models.Pokemon.PokemonResponse
import com.example.pokedex_mvvm.repository.PokemonRepository
import com.example.pokedex_mvvm.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class PokemonListViewModel(val pokemonRepository: PokemonRepository) : ViewModel(){

    val pokemonList : MutableLiveData<Resource<PokemonResponse>> = MutableLiveData()
    var pokemonResponse : PokemonResponse? = null


    init {
        getAllPokemons()
    }

    fun getAllPokemons() = viewModelScope.launch{
        pokemonList.postValue(Resource.Loading())
        val response = pokemonRepository.getAllPokemons()
        pokemonList.postValue(handlegetAllPokemonsResponse(response))

    }

    private fun handlegetAllPokemonsResponse(response: Response<PokemonResponse>) : Resource<PokemonResponse> {
        if(response.isSuccessful){
            response.body()?.let {resultResponse ->
                return Resource.Success(pokemonResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}