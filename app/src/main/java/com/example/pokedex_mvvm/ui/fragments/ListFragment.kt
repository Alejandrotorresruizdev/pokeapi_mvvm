package com.example.pokedex_mvvm.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokedex_mvvm.R
import com.example.pokedex_mvvm.adapters.PokemonAdapter
import com.example.pokedex_mvvm.data.Pokemon
import com.example.pokedex_mvvm.ui.MainActivity
import kotlinx.android.synthetic.main.list_fragment.*

class ListFragment : Fragment(R.layout.list_fragment) {

    lateinit var adapter: PokemonAdapter

    var pokemonList = ArrayList<Pokemon>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFakeData()
        initAdapter()

        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)


        adapter.setOnItemClickListener {currentPokemon ->
            val bundle = Bundle().apply {
                putSerializable("pokemon",currentPokemon)
            }

            findNavController().navigate(
                R.id.action_listFragment_to_detailFragment,
                bundle
            )
        }
    }


    private fun initFakeData(){
        pokemonList.add(Pokemon("Pikachu"))
        pokemonList.add(Pokemon("Bulbasur"))
        pokemonList.add(Pokemon("Raichu"))
        pokemonList.add(Pokemon("Charmander"))
        pokemonList.add(Pokemon("Charmaleon"))
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