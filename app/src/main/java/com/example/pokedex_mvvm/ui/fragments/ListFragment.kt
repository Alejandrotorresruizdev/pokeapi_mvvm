package com.example.pokedex_mvvm.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokedex_mvvm.R
import com.example.pokedex_mvvm.adapters.PokemonAdapter
import com.example.pokedex_mvvm.data.Pokemon
import com.example.pokedex_mvvm.models.Pokemon.PokemonResponse
import com.example.pokedex_mvvm.models.Pokemon.Result
import com.example.pokedex_mvvm.ui.MainActivity
import com.example.pokedex_mvvm.ui.view_models.PokemonListViewModel
import com.example.pokedex_mvvm.utils.Resource
import kotlinx.android.synthetic.main.list_fragment.*

class ListFragment : Fragment(R.layout.list_fragment) {

    private val pokemonList : MutableList<Result> = mutableListOf()
    lateinit var viewModel : PokemonListViewModel
    lateinit var adapter: PokemonAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

        initAdapter()

        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        viewModel.pokemonList.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success -> {
                    response.data?.let { pokemonResponse ->
                        pokemonList.addAll(pokemonResponse.results)
                        adapter.notifyDataSetChanged()
                    }
                }
                is Resource.Error -> {

                }
                is Resource.Loading -> {
                }
            }

        })

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