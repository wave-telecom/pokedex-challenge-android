package br.com.wave.pokedex_challenge_android

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(private val pokemonRepository: PokemonRepository) : ViewModel() {

    private val _pokemonList = MutableLiveData<List<Pokemon>>()
    val pokemonList: LiveData<List<Pokemon>> = _pokemonList

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        fetchPokemonList()
    }

    private fun fetchPokemonList() {
        viewModelScope.launch {
            try {
                val list = pokemonRepository.getPokemonList()
                _pokemonList.value = list
            } catch (e: Exception) {
                Log.e("MainViewModel", "Error fetching Pokemon list", e)
                _error.value = "Error fetching Pokemon list"
            }
        }
    }
}

class MainViewModelFactory(private val pokemonRepository: PokemonRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
        return MainViewModel(pokemonRepository) as T
    }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}