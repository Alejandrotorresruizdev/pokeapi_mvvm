package com.example.pokedex_mvvm.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokedex_mvvm.R
import com.example.pokedex_mvvm.adapters.PokemonAdapter
import com.example.pokedex_mvvm.data.Pokemon
import kotlinx.android.synthetic.main.list_fragment.*

class ListFragment : Fragment(R.layout.list_fragment) {

    var adapter: PokemonAdapter? = null
    var pokemonList = ArrayList<Pokemon>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFakeData()
        initAdapter()
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
        rvPokemonItems.layoutManager = GridLayoutManager(context,2)
        //rvPokemonItems.addItemDecoration(GridItemDecoration(25, 2))

        // Le asignamos el adaptador al RecyclerView
        rvPokemonItems.adapter = adapter
    }
}