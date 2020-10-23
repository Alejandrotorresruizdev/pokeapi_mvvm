package com.example.pokedex_mvvm.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
                    hideProgressBar()
                    response.data?.let { pokemonResponse ->
                        pokemonList.clear()
                        pokemonList.addAll(pokemonResponse.results)
                        adapter.notifyDataSetChanged()
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

        adapter.setOnItemClickListener {currentPokemon ->
            val bundle = Bundle().apply {
                putInt("id",currentPokemon)
            }

            findNavController().navigate(
                R.id.action_listFragment_to_detailFragment,
                bundle
            )
        }
    }

    private fun hideProgressBar () {
        paginationProgressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
        isLoading = true
    }

    var isLoading = false
    var isScrolling = false

    val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as GridLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= 10
            val shouldPaginate = isLastItem && isNotAtBeginning
                    && isTotalMoreThanVisible && isScrolling

            if(shouldPaginate){
                viewModel.getAllPokemons()
                isScrolling = false;
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                isScrolling = true
            }
        }
    }

    private fun initAdapter() {
        // Instanciamos el adaptador
        adapter = PokemonAdapter(pokemonList)

        // Le asignamos el layout que queremos utilizar
        rvPokemonItems.layoutManager = GridLayoutManager(context,2)
        //rvPokemonItems.addItemDecoration(GridItemDecoration(25, 2))

        rvPokemonItems.addOnScrollListener(scrollListener)
        // Le asignamos el adaptador al RecyclerView
        rvPokemonItems.adapter = adapter

    }
}