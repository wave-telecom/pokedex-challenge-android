package br.com.wave.pokedex_challenge_android

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var pokemonAdapter: PokemonAdapter
    private val pokemonRepository = PokemonRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.taskListRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        pokemonAdapter = PokemonAdapter(emptyList())
        recyclerView.adapter = pokemonAdapter

        lifecycleScope.launch {
            delay(1000)
            try {
                pokemonAdapter.updateData(pokemonRepository.getPokemonList())
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error fetching Pokemon list", e)
            }
        }
    }
}