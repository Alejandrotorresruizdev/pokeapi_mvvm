package com.example.pokedex_mvvm.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex_mvvm.R
import com.example.pokedex_mvvm.models.Pokemon.Result
import com.example.pokedex_mvvm.utils.Constants.Companion.BASE_IMG_URL
import kotlinx.android.synthetic.main.pokemon_item.view.*

class PokemonAdapter : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    inner class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item,parent,false)
        return PokemonViewHolder(view)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val currentPokemonItem = differ.currentList[position]

        holder.itemView.apply {
            tvName.text = currentPokemonItem.name.capitalize()
            tvNumber.text = "#"+(position + 1).toString()
            Glide.with(this)
                .load(BASE_IMG_URL+"${position+1}.png")
                .thumbnail(0.25f)
                .into(imageView)
            setOnClickListener {
                onItemClickListener?.let { it(position) }
            }

        }

    }

    private var onItemClickListener: ((Int) -> Unit)? = null

   fun setOnItemClickListener(listener: (Int) -> Unit) {
        onItemClickListener = listener
    }

}