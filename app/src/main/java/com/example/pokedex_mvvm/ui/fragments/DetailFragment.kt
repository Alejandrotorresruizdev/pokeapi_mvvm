package com.example.pokedex_mvvm.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokedex_mvvm.R
import com.example.pokedex_mvvm.adapters.PokemonAdapter
import com.example.pokedex_mvvm.data.Pokemon
import kotlinx.android.synthetic.main.list_fragment.*

class DetailFragment : Fragment(R.layout.details_fragment) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)

    }

}