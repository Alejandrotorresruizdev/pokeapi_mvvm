package com.example.pokedex_mvvm.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.pokedex_mvvm.R
import com.example.pokedex_mvvm.ui.MainActivity
import com.example.pokedex_mvvm.utils.Constants
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.details_fragment.*

import kotlinx.android.synthetic.main.pokemon_item.tvName
import kotlinx.android.synthetic.main.pokemon_item.view.*

class DetailFragment : Fragment(R.layout.details_fragment) {

    val safeArgs: DetailFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //var gt = this.arguments?.getSerializable("pokemon")
        Log.i("Snackbar", "carga?");

        /*Glide.with(this)
            .asGif()
            .load(Constants.BASE_GIF_URL +"${4}.gif")
            .thumbnail(0.25f)
            .into(imageView)*/


    }

}