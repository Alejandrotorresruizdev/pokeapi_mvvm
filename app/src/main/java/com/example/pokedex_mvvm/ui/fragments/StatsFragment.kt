package com.example.pokedex_mvvm.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pokedex_mvvm.R
import com.example.pokedex_mvvm.models.PokemonById.PokemonByIdResult
import com.example.pokedex_mvvm.repository.PokemonRepository
import com.example.pokedex_mvvm.ui.MainActivity
import com.example.pokedex_mvvm.ui.PokemonDetailsViewModelProviderFactory
import com.example.pokedex_mvvm.ui.view_models.PokemonDetailsViewModel
import com.example.pokedex_mvvm.utils.Resource
import kotlinx.android.synthetic.main.stats_fragment.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DemoObjectFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StatsFragment : Fragment(R.layout.stats_fragment) {


    lateinit var pokemonDetails : PokemonByIdResult
    lateinit var viewModel: PokemonDetailsViewModel

    companion object{
        private const val ARG_OBJECT = "object"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.stats_fragment, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModelDetails

        val pokeDetails = viewModel.pokemonDetails.value

        val pokeStats = pokeDetails?.data?.stats;


        if (pokeStats != null) {
            for (stat in pokeStats){
                Log.e("stats", stat.toString())
            }
        }




    }
}