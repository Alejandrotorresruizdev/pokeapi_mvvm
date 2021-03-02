package com.example.pokedex_mvvm.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.pokedex_mvvm.R
import com.example.pokedex_mvvm.adapters.ViewPageAdapter
import com.example.pokedex_mvvm.models.PokemonById.PokemonByIdResult
import com.example.pokedex_mvvm.ui.MainActivity
import com.example.pokedex_mvvm.ui.view_models.PokemonDetailsViewModel
import com.example.pokedex_mvvm.utils.Constants
import com.example.pokedex_mvvm.utils.Resource
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.details_fragment.*


class DetailFragment : Fragment(R.layout.details_fragment){


    lateinit var pokemonDetails : PokemonByIdResult
    //lateinit var pokemonDescription : PokemonDescription

    lateinit var viewModel: PokemonDetailsViewModel

    var pokemonId : Int = 0

    private val adapter by lazy { activity?.let { ViewPageAdapter(it) } }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        pokemonId = this.arguments?.getInt("id") as Int
        viewModel = (activity as MainActivity).viewModelDetails

        initRepository()

        initObservables()

        initViewPagerAdapter()
    }

    private fun initRepository () {
        viewModel.getPokemonById(pokemonId+1)
        viewModel.getDescriptionPokemon(pokemonId+1)
    }

    private fun initObservables () {
        viewModel.pokemonDetails.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { pokemonResponse ->
                        pokemonDetails = pokemonResponse
                        initUI()
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }

        })
    }

    private fun hideProgressBar () {
        progressBar.visibility = View.INVISIBLE
        layout.visibility = View.VISIBLE
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
        layout.visibility = View.INVISIBLE
    }


    private fun initViewPagerAdapter(){
        pager.adapter = adapter

        val tabLayoutMediator =  TabLayoutMediator(tabLayout, pager) { tab, position ->
            when(position){
                0 -> {
                    tab.text ="Description"
                }
                1 -> {
                    tab.text ="Stats"
                }
            }
        }
        tabLayoutMediator.attach()
    }


    private fun initUI() {

        tvName.text = pokemonDetails.name.capitalize()
        tvNumberPokedex.text = "#"+(pokemonDetails.id).toString()
        tvWeight.text = "Weight: "+ pokemonDetails.weight.toString()
        Glide.with(this)
           .load(Constants.BASE_IMG_URL +"${pokemonDetails.id}.png")
           .thumbnail(0.25f)
           .into(ivPokemon)


        for (type in pokemonDetails.types) {
            if(type.slot == 1) tvType1.text = type.type.name
            if(type.slot == 2) tvType2.text = type.type.name
        }

    }

}