package com.example.pokedex_mvvm.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokedex_mvvm.R
import androidx.lifecycle.Observer
import com.example.pokedex_mvvm.models.PokemonDescription.PokemonDescription
import com.example.pokedex_mvvm.ui.MainActivity
import com.example.pokedex_mvvm.ui.view_models.PokemonDetailsViewModel
import com.example.pokedex_mvvm.utils.Resource
import kotlinx.android.synthetic.main.fragment_description.*

class DescriptionFragment : Fragment(R.layout.fragment_description) {

    lateinit var viewModel: PokemonDetailsViewModel
    private var pokemonDescription: PokemonDescription? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_description, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModelDetails

        initObservables()

    }

    private fun initObservables () {
        viewModel.pokemonDescription.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.let { pokemonResponse ->
                        pokemonDescription = pokemonResponse
                        renderDescription()
                    }
                }
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
            }

        })
    }

    private fun renderDescription() {
        if(pokemonDescription !== null) {
            val descriptionText = pokemonDescription!!.flavor_text_entries[0].flavor_text.replace("\\s+".toRegex()," ")
            tvDescription.text = descriptionText;
        }

    }

}