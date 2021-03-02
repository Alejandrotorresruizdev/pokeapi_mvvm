package com.example.pokedex_mvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.pokedex_mvvm.R
import com.example.pokedex_mvvm.repository.PokemonRepository
import com.example.pokedex_mvvm.ui.view_models.PokemonDetailsViewModel
import com.example.pokedex_mvvm.ui.view_models.PokemonListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: PokemonListViewModel
    lateinit var viewModelDetails: PokemonDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initCustomToolbar()

        initViewModels()

        bottomNavigationView.setupWithNavController(appNavHostFragment.findNavController())
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.appNavHostFragment).navigateUp()
    }

    private fun initViewModels(){
        val pokemonRepository = PokemonRepository()

        val viewModelProviderFactory = PokemonViewModellProviderFactory(pokemonRepository)
        val viewModelProviderFactoryDescription = PokemonDetailsViewModelProviderFactory(pokemonRepository)

        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(PokemonListViewModel::class.java)
        viewModelDetails = ViewModelProvider(this,viewModelProviderFactoryDescription).get(PokemonDetailsViewModel::class.java)
    }

    private fun initCustomToolbar() {
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM;
        supportActionBar?.setCustomView(R.layout.custom_toolbar)
    }


}