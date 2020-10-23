package com.example.pokedex_mvvm.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.pokedex_mvvm.R
import com.example.pokedex_mvvm.adapters.ViewPageAdapter
import com.example.pokedex_mvvm.models.Pokemon.Result
import com.example.pokedex_mvvm.models.PokemonById.PokemonByIdResult
import com.example.pokedex_mvvm.repository.PokemonRepository
import com.example.pokedex_mvvm.ui.MainActivity
import com.example.pokedex_mvvm.ui.PokemonDetailsViewModelProviderFactory
import com.example.pokedex_mvvm.ui.PokemonViewModellProviderFactory
import com.example.pokedex_mvvm.ui.view_models.PokemonDetailsViewModel
import com.example.pokedex_mvvm.ui.view_models.PokemonListViewModel
import com.example.pokedex_mvvm.utils.Constants
import com.example.pokedex_mvvm.utils.Resource
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.details_fragment.*
import kotlinx.android.synthetic.main.list_fragment.*


class DetailFragment : Fragment(R.layout.details_fragment){


    private val adapter by lazy { activity?.let { ViewPageAdapter(it) } }

    val safeArgs: DetailFragmentArgs by navArgs()

    lateinit var pokemonDetails : PokemonByIdResult
    lateinit var viewModel: PokemonDetailsViewModel

    var pokemonId : Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //itemPokemon = this.arguments?.getSerializable("pokemon") as Result

        pokemonId = this.arguments?.getInt("id") as Int

        initViewModel()
        viewModel.getPokemonById(pokemonId+1)

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

        initViewPagerAdapter()

    }

    private fun hideProgressBar () {
        progressBar.visibility = View.INVISIBLE
        layout.visibility = View.VISIBLE
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
        layout.visibility = View.INVISIBLE
    }

    fun initViewModel(){
        val pokemonRepository = PokemonRepository()
        val viewModelProviderFactory = PokemonDetailsViewModelProviderFactory(pokemonRepository)

        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(PokemonDetailsViewModel::class.java)
    }

    fun initViewPagerAdapter(){
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


    fun initUI() {
        tvName.text = pokemonDetails.name
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