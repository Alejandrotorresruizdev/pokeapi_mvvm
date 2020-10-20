package com.example.pokedex_mvvm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex_mvvm.R
import com.example.pokedex_mvvm.models.Pokemon.PokemonResponse
import com.example.pokedex_mvvm.models.Pokemon.Result
import com.example.pokedex_mvvm.utils.Constants.Companion.BASE_GIF_URL
import kotlinx.android.synthetic.main.pokemon_item.view.*

class PokemonAdapter(val pokemonList:MutableList<Result>) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item,parent,false)
        return PokemonViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val currentPokemonItem = pokemonList[position]

        holder.itemView.apply {
            tvName.text = currentPokemonItem.name
            tvNumber.text = "#"+(position + 1).toString()
            Glide.with(this)
                .asGif()
                .load(BASE_GIF_URL+"${position+1}.gif")
                .thumbnail(0.25f)
                .into(imageView)
                setOnClickListener {
                onItemClickListener?.let { it(currentPokemonItem) }
            }
        }

    }
    private var onItemClickListener: ((Result) -> Unit)? = null

    fun setOnItemClickListener(listener: (Result) -> Unit) {
        onItemClickListener = listener
    }

}