package br.com.wave.pokedex_challenge_android

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PokemonAdapter(private var pokemonList: List<Pokemon>) :
    RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.pokemonNameTextView)
        val imageView: ImageView = itemView.findViewById(R.id.pokemonImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_list_item, parent, false)
        return PokemonViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val currentPokemon = pokemonList[position]
        val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${getPokemonId(currentPokemon.url)}.png"
        Glide.with(holder.itemView.context)
            .load(imageUrl)
            .into(holder.imageView)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, PokemonDetailsActivity::class.java)
            intent.putExtra("pokemonName", currentPokemon.name)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    fun updateData(newPokemonList: List<Pokemon>) {
        pokemonList = newPokemonList
        notifyDataSetChanged()
    }

    private fun getPokemonId(url: String): String {
        return url.split("/").dropLast(1).last()
    }
}