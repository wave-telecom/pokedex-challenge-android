package br.com.wave.pokedex_challenge_android

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide

class PokemonDetailsActivity : AppCompatActivity() {
    private lateinit var viewModel: PokemonDetailsViewModel
    private val pokemonRepository = PokemonRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_details)

        val pokemonName = intent.getStringExtra("pokemonName")!!

        val pokemonImageView = findViewById<ImageView>(R.id.pokemonImageView)
        val pokemonNameTextView = findViewById<TextView>(R.id.pokemonNameTextView)
        val pokemonTypesTextView = findViewById<TextView>(R.id.pokemonTypesTextView)
        val pokemonAbilitiesTextView = findViewById<TextView>(R.id.pokemonAbilitiesTextView)
        val pokemonStatsTextView = findViewById<TextView>(R.id.pokemonStatsTextView)

        val factory = PokemonDetailsViewModelFactory(pokemonRepository)
        viewModel = ViewModelProvider(this, factory)[PokemonDetailsViewModel::class.java]

        viewModel.pokemonDetails.observe(this) { pokemonDetails ->
            pokemonNameTextView.text = pokemonDetails.name
            Glide.with(this)
                .load(pokemonDetails.sprites.front_default)
                .into(pokemonImageView)

            val types = pokemonDetails.types.joinToString { it.type.name }
            pokemonTypesTextView.text = getString(R.string.types, types)

            val abilities = pokemonDetails.abilities.joinToString { it.ability.name }
            pokemonAbilitiesTextView.text = getString(R.string.abilities, abilities)

            val stats = pokemonDetails.stats.joinToString { "${it.stat.name}: ${it.base_stat}" }
            pokemonStatsTextView.text = getString(R.string.stats, stats)
        }

        viewModel.error.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }

        viewModel.fetchPokemonDetails(pokemonName)
    }
}