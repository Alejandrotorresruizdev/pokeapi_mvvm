package com.example.pokedex_mvvm.ui.view_models

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex_mvvm.models.PokemonById.PokemonByIdResult
import com.example.pokedex_mvvm.repository.PokemonRepository
import com.example.pokedex_mvvm.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class PokemonDetailsViewModel(val pokemonRepository: PokemonRepository) : ViewModel() {

    val pokemonDetails : MutableLiveData <Resource<PokemonByIdResult>> = MutableLiveData()
    var pokemonByIdResult : PokemonByIdResult? = null


    fun getPokemonById(id: Int ) = viewModelScope.launch{
        pokemonDetails.postValue(Resource.Loading())
        val response = pokemonRepository.getPokemonById(id)
        pokemonDetails.postValue(handlePokemonByIdResponse(response))
        Log.i("nuevo",response.toString())
    }

    private fun handlePokemonByIdResponse(response: Response<PokemonByIdResult>): Resource<PokemonByIdResult>? {
            if(response.isSuccessful){
                response.body()?.let {resultResponse->
                    Log.i("details",resultResponse.toString())
                    return Resource.Success(pokemonByIdResult ?: resultResponse)
                }
            }
        return Resource.Error(response.message())
    }
}