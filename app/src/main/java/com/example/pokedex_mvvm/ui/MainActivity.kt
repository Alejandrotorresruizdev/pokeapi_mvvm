package com.example.pokedex_mvvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex_mvvm.R
import com.example.pokedex_mvvm.adapters.PokemonAdapter
import com.example.pokedex_mvvm.data.Pokemon
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.pokemon_item.view.*

class MainActivity : AppCompatActivity() {

    var adapter: PokemonAdapter? = null
    var pokemonList = ArrayList<Pokemon>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initCustomToolbar()
        initFakeData()
        initAdapter()

    }

    private fun initCustomToolbar() {
        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM;
        supportActionBar?.setCustomView(R.layout.custom_toolbar)
    }
    private fun initFakeData(){
        pokemonList.add(Pokemon("Pikachu"))
        pokemonList.add(Pokemon("Pikachu"))
        pokemonList.add(Pokemon("Pikachu"))
        pokemonList.add(Pokemon("Pikachu"))
        pokemonList.add(Pokemon("Pikachu"))
        pokemonList.add(Pokemon("Pikachu"))
        pokemonList.add(Pokemon("Pikachu"))
        pokemonList.add(Pokemon("Pikachu"))
        pokemonList.add(Pokemon("Pikachu"))
        pokemonList.add(Pokemon("Pikachu"))
    }

    private fun initAdapter() {
        // Instanciamos el adaptador
        adapter = PokemonAdapter(pokemonList)

        // Le asignamos el layout que queremos utilizar
        rvPokemonItems.layoutManager = GridLayoutManager(this,2)
        //rvPokemonItems.addItemDecoration(GridItemDecoration(25, 2))

        // Le asignamos el adaptador al RecyclerView
        rvPokemonItems.adapter = adapter
    }
}