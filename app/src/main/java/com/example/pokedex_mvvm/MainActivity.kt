package com.example.pokedex_mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex_mvvm.data.Pokemon
import com.example.pokedex_mvvm.utils.GridItemDecoration
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.pokemon_item.*
import kotlinx.android.synthetic.main.pokemon_item.view.*

class MainActivity : AppCompatActivity() {

    var adapter: PokemonAdapter? = null
    var pokemonList = ArrayList<Pokemon>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        // Instanciamos el adaptador
        adapter = PokemonAdapter(pokemonList)

        // Le asignamos el layout que queremos utilizar
        rvPokemonItems.layoutManager = GridLayoutManager(this,2)
        //rvPokemonItems.addItemDecoration(GridItemDecoration(25, 2))

        // Le asignamos el adaptador al RecyclerView
        rvPokemonItems.adapter = adapter
    }
}

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