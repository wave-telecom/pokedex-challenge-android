package br.com.wave.pokedex_challenge_android

class PokemonRepository {
    suspend fun getPokemonList(): List<Pokemon> {
        return Retrofit.api.getPokemonList().results
    }

    suspend fun getPokemonDetails(name: String): PokemonDetails {
        return Retrofit.api.getPokemonDetails(name)
    }
}