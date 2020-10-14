package com.example.pokedex_mvvm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex_mvvm.R
import com.example.pokedex_mvvm.data.Pokemon
import kotlinx.android.synthetic.main.pokemon_item.view.*

class PokemonAdapter(
    var pokemonList: ArrayList<Pokemon>
) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item,parent,false)
        return PokemonViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val currentPokemonItem = pokemonList[position]

        holder.itemView.tvName.text = currentPokemonItem.name
    }


}