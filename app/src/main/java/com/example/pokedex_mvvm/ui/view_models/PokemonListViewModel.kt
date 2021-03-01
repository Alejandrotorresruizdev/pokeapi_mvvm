package com.example.pokedex_mvvm.ui.view_models

import android.util.Log
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

    var pokemonPages = 0
    private var offset = 0
    private var limit = 10

    init {
        getAllPokemons()
        Log.d("response", "gest all")
    }

     fun getAllPokemons() = viewModelScope.launch{
        pokemonList.postValue(Resource.Loading())
        val response = pokemonRepository.getAllPokemons(limit,offset)
        pokemonList.postValue(handlegetAllPokemonsResponse(response))
         Log.d("response22", response?.toString())


     }

    private fun handlegetAllPokemonsResponse(response: Response<PokemonResponse>) : Resource<PokemonResponse> {
        if(response.isSuccessful){
            response.body()?.let {resultResponse ->
                pokemonPages++
                offset = pokemonPages * 10
                limit = 10
                if(pokemonResponse == null){
                    pokemonResponse = resultResponse
                }else{
                    val oldPokemons = pokemonResponse?.results
                    val newPokemons = resultResponse.results
                    oldPokemons?.addAll(newPokemons)
                }
                return Resource.Success(pokemonResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}