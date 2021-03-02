package com.example.pokedex_mvvm.ui.view_models

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex_mvvm.models.PokemonById.PokemonByIdResult
import com.example.pokedex_mvvm.models.PokemonDescription.PokemonDescription
import com.example.pokedex_mvvm.repository.PokemonRepository
import com.example.pokedex_mvvm.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import kotlin.random.Random

class PokemonDetailsViewModel(val pokemonRepository: PokemonRepository) : ViewModel() {

    val pokemonDetails : MutableLiveData <Resource<PokemonByIdResult>> = MutableLiveData()
    val pokemonDescription : MutableLiveData <Resource<PokemonDescription>> = MutableLiveData()

    var pokemonByIdResult : PokemonByIdResult? = null
    var pokemonDescriptionResult : PokemonDescription? = null


    fun getPokemonById(id: Int ) = viewModelScope.launch{
        pokemonDetails.postValue(Resource.Loading())
        val response = pokemonRepository.getPokemonById(id)
        pokemonDetails.postValue(handlePokemonByIdResponse(response))
    }

    fun getDescriptionPokemon(id:Int) = viewModelScope.launch{
        pokemonDescription.postValue(Resource.Loading())
        val response = pokemonRepository.getDescriptionPokemon(id)
        pokemonDescription.postValue(handlePokemonDescriptionResponse(response))
    }

    private fun handlePokemonByIdResponse(response: Response<PokemonByIdResult>): Resource<PokemonByIdResult>? {
            if(response.isSuccessful){
                response.body()?.let {resultResponse->
                    return Resource.Success(pokemonByIdResult ?: resultResponse)
                }
            }
        return Resource.Error(response.message())
    }

    private fun handlePokemonDescriptionResponse(response: Response<PokemonDescription>): Resource<PokemonDescription>? {
        if(response.isSuccessful){
            response.body()?.let {resultResponse->
                return Resource.Success(pokemonDescriptionResult ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}