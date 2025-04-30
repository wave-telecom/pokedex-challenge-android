package br.com.wave.pokedex_challenge_android

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PokemonDetailsViewModel(private val pokemonRepository: PokemonRepository) : ViewModel() {

    private val _pokemonDetails = MutableLiveData<PokemonDetails>()
    val pokemonDetails: LiveData<PokemonDetails> = _pokemonDetails

    private val _error = MutableLiveData<String>()
    val error:LiveData<String> = _error

    fun fetchPokemonDetails(name: String) {
        viewModelScope.launch {
            delay(1000)
            try {
                val details = pokemonRepository.getPokemonDetails(name)
                _pokemonDetails.value = details
            } catch (e: Exception) {
                Log.e("PokemonDetailsViewModel", "Error fetching Pokemon details", e)
                _error.value = "Error fetching Pokemon details"
            }
        }
    }
}

class PokemonDetailsViewModelFactory(private val pokemonRepository: PokemonRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonDetailsViewModel::class.java)) {
            return PokemonDetailsViewModel(pokemonRepository) as T
        }
        throw IllegalArgumentException("UnknownViewModel class")
    }
}